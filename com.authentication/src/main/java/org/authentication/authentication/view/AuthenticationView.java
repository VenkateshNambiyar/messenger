package org.authentication.authentication.view;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.authentication.authentication.controller.AuthenticationController;
import org.authentication.authentication.exception.UserNotFoundException;
import org.authentication.authentication.exception.UsernameAlreadyExistsException;
import org.authentication.authentication.model.Authentication;
import org.authentication.authentication.validation.Validation;
import java.util.Scanner;


/**
 * AuthenticationView class represents the visualization to user and data received from the model.
 */
public class AuthenticationView {
    private static final Logger LOGGER = Logger.getLogger(AuthenticationView.class);
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * propertyLogger()methods have a property logger Configuration to trace out the errors
     */
    public static void propertyLogger() {
        final String logger = "C:/Users/venka/Documents/TZ/messenger/Properties Files/Logger.properties";

        PropertyConfigurator.configure(logger);
    }

    /**
     * assignUsername() methods to get a username from user
     * @return username
     */
    public String assignUsername() {
        LOGGER.info("Enter UserName :\t");
        final String username = SCANNER.nextLine();

        if (!Validation.validateUsername(username)) {
            return assignUsername();
        }

        return username;
    }

    /**
     * assignPassword() methods to get a password from user
     * @return password
     */
    public String assignPassword() {
        LOGGER.info("Enter Password :\t");
        final String password = SCANNER.nextLine();

        if (!Validation.validatePassword(password)) {
            return assignPassword();
        }

        return password;
    }

    /**
     * selectAuthenticationPortal method to get a user selecting option.
     * @return selectAuthenticationOption
     */
    public String selectAuthenticationPortal() {
        LOGGER.info("Select Anyone Operation : 1)SignIn \t 2)SignUp \t 3)ForgotPassword\t");
        LOGGER.info("Select Option : \t");
        final Long selectAuthentication = SCANNER.nextLong();

        if (!Validation.validateAuthenticationSelection(String.valueOf(selectAuthentication))) {
            return selectAuthenticationPortal();
        }

        return String.valueOf(selectAuthentication);
    }

    /**
     * authentication() get a username and password details from user for authentication process in Messenger
     * @return authentication
     */
    public Authentication authentication() {
        final Authentication authentication = Authentication.getInstance();

        authentication.setUsername(assignUsername());
        authentication.setPassword(assignPassword());

        return authentication;
    }

    /**
     * signIn() methods provides a  user to log_in their account
     */
    public void signIn() {
        final Authentication authentication = authentication();

        final AuthenticationController authenticationController = new AuthenticationController();

        try {
            if (authenticationController.signIn(authentication)) {
                LOGGER.info("Successfully Login");
            } else {
                LOGGER.info("Invalid.Try again");

                signIn();
            }
        } catch (UserNotFoundException exception) {
            LOGGER.info("UserNotFound");

            signIn();
        }
    }

    /**
     * signUp() methods provides a user to create a new Account
     */
    public void signUp() {
        final Authentication authentication = authentication();

        final AuthenticationController authenticationController = new AuthenticationController();

        try {
            if(!authenticationController.signUp(authentication)) {
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
     * changePassword() methods provides to validate a username for update new password
     */
    public void changePassword() {
        final Authentication authentication = authentication();

        final AuthenticationController authenticationController = new AuthenticationController();

        try {
            if (!authenticationController.changePassword(authentication)) {
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
     * selectAuthentication() method provide option to user to choose different stages in Authentication.
     */
    public void selectAuthentication() {
        propertyLogger();

        switch (selectAuthenticationPortal()) {
        case "1" -> signIn();
        case "2" -> signUp();
        case "3" -> changePassword();
        default -> LOGGER.warn("Invalid Selection");
        }
    }
}