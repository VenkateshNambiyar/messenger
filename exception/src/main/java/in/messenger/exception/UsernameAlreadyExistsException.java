package in.messenger.exception;

/**
 *uses custom exception UsernameAlreadyExistsException
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class UsernameAlreadyExistsException extends CustomException {

    public UsernameAlreadyExistsException(final String exception) {
        super(exception);
    }
}