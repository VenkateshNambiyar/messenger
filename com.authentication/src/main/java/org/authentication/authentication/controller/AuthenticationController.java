package org.authentication.authentication.controller;


import org.authentication.authentication.model.Authentication;
import org.authentication.authentication.service.AuthenticationServiceImplementation;


/**
 * AuthenticationController controls the data flow into model object and updates the view whenever data changes
 */
public class AuthenticationController {

    private static final AuthenticationServiceImplementation AUTHENTICATION_SERVICE_IMPLEMENTATION =
            new AuthenticationServiceImplementation();

    /**
     * signIn() method send a Model data into Services.
     * @param authentication    represent an Authentication class object.
     * @return    a boolean value from Services
     */
    public boolean signIn(final Authentication authentication) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.login(authentication);
    }

    /**
     * signUp() method send a Model data into Services.
     * @param authentication represent an Authentication class object.
     * @return  a boolean value from Services
     */
    public boolean signUp(final Authentication authentication) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.signUp(authentication);
    }

    /**
     * changePassword() methods send a Model Data into Services
     * @param authentication represent an Authentication class object
     * @return a boolean value from A Services
     */
    public boolean changePassword(final Authentication authentication) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.updatePassword(authentication);
    }
}