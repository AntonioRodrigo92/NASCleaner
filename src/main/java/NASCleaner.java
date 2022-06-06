import Exceptions.NotADirectoryException;
import Utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class NASCleaner {
    private static final Logger LOG = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        LOG.info("beginning the cleaning process");
        String propertiesFile = args[0];

        String rootDirPath = Utils.baseDirectory(propertiesFile);
        String securityDir = Utils.securityDirectory(propertiesFile);
        Float threshold = Float.parseFloat(Utils.threshold(propertiesFile));

        Float diskUsage = Utils.getPercentualDiskUsage(rootDirPath);

        try {
            while (diskUsage > threshold) {
                LOG.info("disk usage is over the threshold");

                File oldest = Utils.getOldestDirectory(securityDir);
                Utils.deleteDirectory(oldest);
                diskUsage = Utils.getPercentualDiskUsage(rootDirPath);

                LOG.info("the " + oldest.getName() + " directory was deleted");
            }
        } catch (NotADirectoryException e) {
            LOG.error(e);
        } catch (Exception e) {
            LOG.error(e);
        }
        LOG.info("Cleaning process done!!");
    }
}
