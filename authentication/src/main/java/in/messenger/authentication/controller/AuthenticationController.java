package in.messenger.authentication.controller;

import in.messenger.authentication.model.Authentication;
import in.messenger.authentication.service.AuthenticationServiceImplementation;

import java.util.List;

/**
 * the data getting from user model object and updates the view whenever data changes
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class AuthenticationController {
    private static final AuthenticationServiceImplementation AUTHENTICATION_SERVICE_IMPLEMENTATION =
            new AuthenticationServiceImplementation();

    /**
     * to get a userDetails send a data into Services.
     *
     * @param username represent an user request.
     * @return a boolean value from Services
     */
    public List<Authentication> userDetails(final String username) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.userDetails(username);
    }

    /**
     * to add a new user send the data into Services
     * @param authentication represent an Authentication class object.
     * @return  a boolean value from Services
     */
    public boolean addUser(final Authentication authentication) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.addUser(authentication);
    }

    /**
     * to update a password send a data into Services
     *
     * @param authentication represent an Authentication class object
     * @return a boolean value from A Services
     */
    public boolean changePassword(final Authentication authentication) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.updatePassword(authentication);
    }

    /**
     * to get an all user details send data into Services
     */
    public List<Authentication>getAllDetails() {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.getAllDetails();
    }


    /**
     * to delete a user and send their data into Services
     */
    public boolean deleteUser(final String username) {
        return AUTHENTICATION_SERVICE_IMPLEMENTATION.deleteUser(username);
    }
}