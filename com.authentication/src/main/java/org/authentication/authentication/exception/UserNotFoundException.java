package org.authentication.authentication.exception;


/**
 * UserNotFoundException is an UserDefinedException and its extends a CustomException
 */
public class UserNotFoundException extends CustomException {

    public UserNotFoundException(final String exception) {
        super(exception);
    }
}