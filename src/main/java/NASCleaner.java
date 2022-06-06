import Exceptions.NotADirectoryException;
import Utils.Utils;

import java.io.File;

public class NASCleaner {

    public static void main(String[] args) {
        String propertiesFile = args[0];

        String rootDirPath = Utils.baseDirectory(propertiesFile);
        String securityDir = Utils.securityDirectory(propertiesFile);
        Float threshold = Float.parseFloat(Utils.threshold(propertiesFile));

        Float diskUsage = Utils.getPercentualDiskUsage(rootDirPath);

        try {
            while (diskUsage > threshold) {
                File oldest = Utils.getOldestDirectory(securityDir);
                Utils.deleteDirectory(oldest);

                System.out.println("deleted " + oldest.getAbsolutePath());
            }
        } catch (NotADirectoryException e) {
            throw new RuntimeException(e);
        }
    }
}
