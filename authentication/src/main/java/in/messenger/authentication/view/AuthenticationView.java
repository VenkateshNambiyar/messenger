package in.messenger.authentication.view;

import in.messenger.authentication.controller.AuthenticationController;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import in.messenger.authentication.model.Authentication;
import in.messenger.exception.UsernameAlreadyExistsException;
import in.messenger.userInput.UserInput;
import in.messenger.validation.Validation;

/**
 * To represents the visualization to user and data received from the model.
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class AuthenticationView {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationView.class);
    private static final AuthenticationController AUTHENTICATION_CONTROLLER = new AuthenticationController();

    /**
     * a property logger Configuration to trace out the errors
     */
    public static void propertyLogger() {
        final String logger = "C:/Users/venka/Documents/TZ/messenger/Properties Files/Logger.properties";

        PropertyConfigurator.configure(logger);
    }

    /**
     * to get a username details from user
     * @return username
     */
    public String assignUsername() {
        LOGGER.info("Enter UserName :\t");
        final String username = UserInput.getString();

        if (!Validation.validateUsername(username)) {
            return assignUsername();
        }
        return username;
    }

    /**
     * to get a password details from user
     * @return password
     */
    public String assignPassword() {
        LOGGER.info("Enter Password :\t");
        final String password = UserInput.getString();

        if (!Validation.validatePassword(password)) {
            return assignPassword();
        }

        return password;
    }

    /**
     * user selecting option for authentication.
     * @return selectAuthenticationOption
     */
    public String selectAuthenticationPortal() {
        LOGGER.info("Select Anyone Operation : 1)SignIn \t 2)SignUp \t 3)ForgotPassword\t");
        LOGGER.info("Select Option : \t");
        final Long selectAuthentication = UserInput.getLong();

        if (!Validation.validateAuthenticationChoiceSelection(String.valueOf(selectAuthentication))) {
            return selectAuthenticationPortal();
        }

        return String.valueOf(selectAuthentication);
    }

    /**
     * to get a username and password details from user for authentication process in Messenger
     * @return authentication
     */
    public Authentication authentication() {
        final Authentication authentication = new Authentication();

        authentication.setUsername(assignUsername());
        authentication.setUsername(assignPassword());

        return authentication;
    }

    /**
     * to get a userDetails
     */
    private void getUserDetails() {
        try {
            AUTHENTICATION_CONTROLLER.userDetails(assignUsername());
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("user Not found");
            getUserDetails();
        }
    }

    /**
     * user to create a new Account
     */
    private void signUp() {
        try {

            if(AUTHENTICATION_CONTROLLER.addUser(authentication())) {
                LOGGER.info("Successfully Account created");
            } else {
                LOGGER.info("Try again");

                signUp();
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username already Exists");

            signUp();
        }
    }

    /**
     * validate a username for update new password
     */
    public void changePassword() {
        final Authentication authentication = authentication();

        try {

            if (AUTHENTICATION_CONTROLLER.changePassword(authentication)) {
                LOGGER.info("Successfully Account created");
            } else {
                LOGGER.info("Change password");

                changePassword();
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username Not Founded");

            changePassword();
        }
    }

    /**
     * provide option to user to choose different stages in Authentication.
     */
    public void selectAuthentication() {
        propertyLogger();

        switch (selectAuthenticationPortal()) {
        case "1" -> getUserDetails();
        case "2" -> signUp();
        case "3" -> changePassword();
        default -> LOGGER.warn("Invalid Selection");
        }
    }
}