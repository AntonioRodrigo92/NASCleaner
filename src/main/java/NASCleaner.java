import Utils.Utils;

import java.io.File;

public class NASCleaner {

    public static void main(String[] args) {
        File disk = new File("/media/antonio/FCB0106CB0102F9E/");
        System.out.println(Utils.getPercentualDiskUsage(disk));
    }
}
