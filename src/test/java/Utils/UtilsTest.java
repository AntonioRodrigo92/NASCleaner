package Utils;

import Exceptions.NotADirectoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class UtilsTest {
    private final String PATH = "C:\\Users\\Antonio\\IdeaProjects\\NASCleaner\\src\\test\\resources\\Properties.conf";

    @Test
    void should_returnCorrectBaseDirectory_when_correctPath() throws IOException {
        //  given
        String expected = "/srv/dev-disk-by-uuid-38012dfb-a7c3-41b2-8451-71d603780523/nas_storage";
        //  when
        String actual = Utils.baseDirectory(PATH);
        //  then
        assertEquals(expected, actual);
    }

    @Test
    void should_throwExceptionBaseDirectory_when_incorrectPath() {
        //  given

        //  when
        Executable executable = () -> Utils.baseDirectory("");
        //  then
        assertThrows(IOException.class, executable);
    }

    @Test
    void should_returnCorrectThreshold_when_correctPath() throws IOException {
        //  given
        String expected = "70.0";
        //  when
        String actual = Utils.threshold(PATH);
        //  then
        assertEquals(expected, actual);
    }

    @Test
    void should_throwExceptionThreshold_when_incorrectPath() {
        //  given

        //  when
        Executable executable = () -> Utils.threshold("");
        //  then
        assertThrows(IOException.class, executable);
    }

    @Test
    void should_returnCorrectDirectoryMaxSize_when_correctPath() throws IOException {
        //  given
        String expected = "35.0";
        //  when
        String actual = Utils.directoryMaxSize(PATH);
        //  then
        assertEquals(expected, actual);
    }

    @Test
    void should_throwExceptionCorrectDirectoryMaxSize_when_incorrectPath() {
        //  given

        //  when
        Executable executable = () -> Utils.directoryMaxSize("");
        //  then
        assertThrows(IOException.class, executable);
    }

    @Test
    void should_returnCorrectSecurityDirectory_when_correctPath() throws IOException {
        //  given
        String expected = "/srv/dev-disk-by-uuid-38012dfb-a7c3-41b2-8451-71d603780523/nas_storage/security_cam_backup";
        //  when
        String actual = Utils.securityDirectory(PATH);
        //  then
        assertEquals(expected, actual);
    }

    @Test
    void should_throwExceptionSecurityDirectory_when_incorrectPath() {
        //  given

        //  when
        Executable executable = () -> Utils.securityDirectory("");
        //  then
        assertThrows(IOException.class, executable);
    }

    @Test
    void should_returnCorrectPercentualDiskUsage_when_correctPath() {
        //  given
        String path = "D:\\Imagens\\OnePlus3\\1-9-2020";
        float expected = (float) 50.998314;
        //  when
        float actual = Utils.getPercentualDiskUsage(path);
        //  then
        assertEquals(expected, actual);
    }

//    @Test
//    void should_deleteDirectory_when_correctPath() throws NotADirectoryException {
//        //  given
//        Utils utils = mock(Utils.class);
//        //  when
//        doNothing().when(utils).deleteDirectory(isA(File.class));
//        utils.deleteDirectory(any());
//        //  then
//        verify(utils, times(1).deleteDirectory(new File("")));
//    }

    @Test
    void should_returnOldestDirectory_when_correctPath() throws NotADirectoryException {
        //  given
        String path = "D:\\Imagens\\OnePlus3";
        File expected = new File("D:\\Imagens\\OnePlus3\\1-9-2020");
        //  when
        File actual = Utils.getOldestDirectory(path);
        //  then
        assertEquals(expected, actual);
    }

    @Test
    void should_throwErrorOldestDirectory_when_NotADirectory() {
        //  given
        String path = "D:\\Imagens\\OnePlus3\\1-9-2020\\00000IMG_00000_BURST20190627210940685_COVER.jpg";
        //  when
        Executable executable = () -> Utils.getOldestDirectory(path);
        //  then
        assertThrows(NotADirectoryException.class, executable);
    }

    @Test
    void should_returnDirectorySize_when_correctPath() {
        //  given
        String path = "D:\\Imagens\\OnePlus3\\1-9-2020";
        double expected = 8.45;
        //  when
        BigDecimal actualBD = new BigDecimal(Utils.directorySizeInGB(path)).setScale(2, RoundingMode.HALF_DOWN);
        double actual = actualBD.doubleValue();
        //  then
        assertEquals(expected, actual);
    }

}