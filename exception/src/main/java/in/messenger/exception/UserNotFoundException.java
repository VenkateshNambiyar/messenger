package in.messenger.exception;

/**
 * uses custom exception UserNotFoundException
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class UserNotFoundException extends CustomException {

    public UserNotFoundException(final String exception) {
        super(exception);
    }
}