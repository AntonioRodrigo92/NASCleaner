import Utils.Utils;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String path = "D:\\Imagens\\OnePlus3";
        File f = new File(path);
        System.out.println(Utils.directorySizeInGB(f));
    }
}
