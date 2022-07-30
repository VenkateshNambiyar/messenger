package org.authentication.authentication.exception;

/**
 * CustomException is an UserDefinedException and its extends a RuntimeException
 */
public class CustomException extends RuntimeException{

    public CustomException(final String exception) {
        super(exception);
    }
}