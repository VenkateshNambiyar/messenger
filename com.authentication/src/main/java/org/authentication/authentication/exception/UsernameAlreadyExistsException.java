package org.authentication.authentication.exception;


/**
 * UsernameAlreadyExistsException is an UserDefinedException and its extends a CustomException
 */
public class UsernameAlreadyExistsException extends CustomException {

    public UsernameAlreadyExistsException(final String exception) {
        super(exception);
    }
}