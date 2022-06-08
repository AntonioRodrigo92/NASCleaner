import Exceptions.NotADirectoryException;
import Utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class NASCleaner {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        LOG.info("Beginning the cleaning process");
        String propertiesFile = args[0];

        String rootDirPath = Utils.baseDirectory(propertiesFile);
        String securityDir = Utils.securityDirectory(propertiesFile);
        Float threshold = Float.parseFloat(Utils.threshold(propertiesFile));
        double maxSize = Double.parseDouble(Utils.directoryMaxSize(propertiesFile));

        Float diskUsage = Utils.getPercentualDiskUsage(rootDirPath);
        double securityDirSize = Utils.directorySizeInGB(securityDir);
        LOG.info("Disk usage is at " + securityDirSize + " (" + diskUsage + "%)");

        try {
            while (diskUsage > threshold || securityDirSize > maxSize) {
                LOG.warn("Disk usage or max size above threshold. Deleting security footage!");

                File oldest = Utils.getOldestDirectory(securityDir);
                Utils.deleteDirectory(oldest);
                diskUsage = Utils.getPercentualDiskUsage(rootDirPath);
                securityDirSize = Utils.directorySizeInGB(securityDir);

                LOG.warn("The " + oldest.getName() + " directory was deleted");
            }
        } catch (NotADirectoryException e) {
            LOG.error(e);
        } catch (Exception e) {
            LOG.error(e);
        }
        LOG.info("Cleaning process done!! \n");
    }
}
