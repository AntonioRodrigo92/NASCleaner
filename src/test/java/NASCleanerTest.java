import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class NASCleanerTest {

    @Test
    void test() throws Exception {
        createTestFiles();

        NASCleaner.main(new String[]{"C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\properties_main.conf"});

        deleteTestFiles();
    }

    private void createTestFiles() throws IOException {
        Path dirPath = Paths.get("C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN");
        Files.createDirectories(dirPath);

        File sourceDirectory = new File("D:\\Imagens\\fotinha");
        File destinationDirectory = new File("C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN");
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    }

    private void deleteTestFiles() throws IOException {
        FileUtils.deleteDirectory(new File("C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN"));
    }

}