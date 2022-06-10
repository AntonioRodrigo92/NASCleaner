import Exceptions.NotADirectoryException;
import Utils.Utils;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class NASCleanerTest {

    @Test
    void should_deleteFiles_when_correctPath() throws Exception {
        //  given
        String dirPath = "C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN";
        String propertiesPath = "C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\properties_main.conf";
        //  when
        float beginning = Utils.getPercentualDiskUsage(dirPath);
        NASCleaner.main(new String[]{propertiesPath});
        float end = Utils.getPercentualDiskUsage(dirPath);
        //  then
        assertTrue(beginning > end);
    }

    @Test
    void should_throwException_when_incorrectPath() {
        //  given
        String propertiesPath = "";
        //  when
        Executable executable = () -> NASCleaner.main(new String[]{propertiesPath});
        //  then
        assertThrows(Exception.class, executable);
    }

    @Disabled
    @Test
    void should_throwNotADirectoryException_when_incorrectPath() {
        //  given
        String propertiesPath = "C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\properties_WRONG_main.conf";
        //  when
        Executable executable = () -> NASCleaner.main(new String[]{propertiesPath});
        //  then
        assertThrows(NotADirectoryException.class, executable);
    }

    @BeforeEach
    private void createTestFiles() throws IOException {
        Path dirPath = Paths.get("C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN");
        Files.createDirectories(dirPath);

        File sourceDirectory = new File("D:\\Imagens\\fotinha");
        File destinationDirectory = new File("C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN");
        FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    }

    @AfterEach
    private void deleteTestFiles() throws IOException {
        FileUtils.deleteDirectory(new File("C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\TESTE_MAIN"));
    }

}