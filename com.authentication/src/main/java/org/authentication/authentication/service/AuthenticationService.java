package org.authentication.authentication.service;


import org.authentication.authentication.model.Authentication;


/**
 * AuthenticationService Interface have a collection of abstract method for Authentication in Messenger.
 */
public interface AuthenticationService {

  boolean login(final Authentication authentication);

  boolean signUp(final Authentication authentication);

  boolean updatePassword(final Authentication authentication);
}