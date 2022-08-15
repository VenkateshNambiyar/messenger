package in.messenger.exception;

/**
 * uses custom exception DatabaseNotConnectedException
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class DatabaseNotConnectedException extends CustomException {

    public DatabaseNotConnectedException(final String exception) {
        super(exception);
    }
}