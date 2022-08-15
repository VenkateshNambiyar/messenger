package in.messenger.exception;

/**
 * CustomException is an UserDefinedException and its extends a RuntimeException
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class CustomException extends RuntimeException{

    public CustomException(final String exception) {
        super(exception);
    }
}