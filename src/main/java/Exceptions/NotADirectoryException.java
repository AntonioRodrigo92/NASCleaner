package Exceptions;

public class NotADirectoryException extends Exception {
    public NotADirectoryException(String errorMessage) {
        super(errorMessage);
    }
}
