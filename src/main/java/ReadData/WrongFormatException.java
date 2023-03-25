package ReadData;

/**
 * An exception occurs if the data format differs from the expected one
 */
public class WrongFormatException extends RuntimeException {
    /**
     * @param message Text to be displayed
     */
    public WrongFormatException(String message) {
        super(message);
    }
}
