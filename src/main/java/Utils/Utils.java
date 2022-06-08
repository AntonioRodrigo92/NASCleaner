package Utils;

import Exceptions.NotADirectoryException;
import Interface.FileFilter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Utils {

    public static String baseDirectory(String filePath) {
        return Utils.getProperty(filePath, "BASE_DIRECTORY");
    }

    public static String threshold(String filePath) {
        return Utils.getProperty(filePath, "THRESHOLD");
    }

    public static String securityDirectory(String filePath) {
        return Utils.getProperty(filePath, "SECURITY_CAM_DIR");
    }

    public static float getPercentualDiskUsage(String rootPath) {
        File disk = new File(rootPath);
        float total = disk.getTotalSpace();
        float free = disk.getFreeSpace();
        float usage = ((total - free) / total) * 100;
        return usage;
    }

    public static void deleteDirectory(File dir) throws NotADirectoryException {
        if (dir.isDirectory()) {
            ArrayList<File> allFiles = (ArrayList<File>) Utils.getAllFilesInDirectory(dir.getAbsolutePath());
            for (File f : allFiles) {
                f.delete();
            }
            dir.delete();
        }
        else {
            throw new NotADirectoryException("The path given is not a directory");
        }
    }

    public static File getOldestDirectory (String path) throws NotADirectoryException {
        File root = new File(path);
        if (root.isDirectory()) {
            List<File> dateFolder = getAllDirectoriesInDirectory(path);
            Optional<File> oldest = dateFolder.stream().max((f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
            return oldest.get();
        }
        else {
            throw new NotADirectoryException("The path given is not a directory");
        }
    }

    public static double directorySizeInGB(File directory) {
        double sizeInBits = FileUtils.sizeOfDirectory(directory);
        double sizeInGB = sizeInBits / (Math.pow(1024, 3));
        return sizeInGB;
    }

    private static String getProperty(String filePath, String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }

    private static List<File> getAllFilesInDirectory(String dirPath) {
        return getAllEntriesInDirectory(dirPath, File::isFile);
    }

    private static List<File> getAllDirectoriesInDirectory(String dirPath) {
        return getAllEntriesInDirectory(dirPath, File::isDirectory);
    }

    private static List<File> getAllEntriesInDirectory(String dirPath, FileFilter filter) {
        File directory = new File(dirPath);
        File[] allFilesInDir = directory.listFiles();
        List<File> fileList = new ArrayList<>();
        for (File f : allFilesInDir) {
            if(filter.condition(f)) {
                fileList.add(f);
            }
        }
        return fileList;
    }

}
