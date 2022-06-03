package Utils;

import Exceptions.NotADirectoryException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public static float getPercentualDiskUsage(File disk) {
        float total = disk.getTotalSpace();
        float free = disk.getFreeSpace();
        float usage = ((total - free) / total) * 100;
        return usage;
    }

    public static void deleteDirectory(String dirPath) throws Exception {
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

    private static List<File> getAllFilesInDirectory(String dirPath) {
        File directory = new File(dirPath);
        File[] allFilesInDir = directory.listFiles();
        List<File> fileList = new ArrayList<>();

        for (File f : allFilesInDir) {
            if(f.isFile()) {
                fileList.add(f);
            }
        }
        return fileList;
    }
}
