package org.authentication.authentication.exception;


/**
 * DatabaseNotConnectedException is an UserDefinedException and its extends a CustomException
 */
public class DatabaseNotConnectedException extends CustomException {

    public DatabaseNotConnectedException(final String exception) {
        super(exception);
    }
}