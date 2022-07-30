package org.authentication.authentication.service;


import org.authentication.authentication.dao.AuthenticationDao;
import org.authentication.authentication.model.Authentication;


/**
 * AuthenticationServiceImplementation class implements a Messenger Authentication abstract methods
 */
public class AuthenticationServiceImplementation implements AuthenticationService {

    /**
     * login() methods provides a  user to log_in their account
     */
    @Override
    public boolean login(final Authentication authentication) {
        return AuthenticationDao.login(authentication);
    }

    /**
     * signUp() methods provides a user to create a new Account
     */
    @Override
    public boolean signUp(Authentication authentication) {
        validateUsername(authentication);

        return AuthenticationDao.addUser(authentication);
    }

    /**
     * changePassword() methods provides to validate a username for update new password
     */
    private boolean validateUsername(final Authentication authentication) {

        if (!AuthenticationDao.validateUsername(authentication)) {
                return validateUsername(authentication);
        }

        return true;
    }

    /**
     * updatePassword() methods provides to change a user password
     */
    @Override
     public boolean updatePassword(final Authentication authentication) {
        validateUsername(authentication);

        return AuthenticationDao.updatePassword(authentication);
     }
}