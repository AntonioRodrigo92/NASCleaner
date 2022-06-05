package Utils;

import Exceptions.NotADirectoryException;
import Interface.FileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class Utils {

    public static String baseDirectory(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("BASE_DIRECTORY");
    }

    public static float getPercentualDiskUsage(String rootPath) {
        File disk = new File(rootPath);
        float total = disk.getTotalSpace();
        float free = disk.getFreeSpace();
        float usage = ((total - free) / total) * 100;
        return usage;
    }

    public static void deleteDirectory(String dirPath) throws NotADirectoryException {
        File dir = new File(dirPath);
        if (dir.isDirectory()) {
            ArrayList<File> allFiles = (ArrayList<File>) Utils.getAllFilesInDirectory(dirPath);
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
