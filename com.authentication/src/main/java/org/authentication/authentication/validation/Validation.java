package org.authentication.authentication.validation;


/**
 * Validation class validate a user information
 */
public class Validation {

    /**
     * validateUsername() methods validate a username using Regex
     * @param username from AuthenticationView class
     * @return username
     */
    public static Boolean validateUsername(final String username) {
        return username.matches("^[a-zA-Z\\d\\s]{6,20}$");
    }

    /**
     * validatePassword() methods validate a password using Regex
     * @param password from AuthenticationView class
     * @return password
     */
    public static Boolean validatePassword(final String password) {
        return password.matches("^(?=.*[\\d])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$");
    }

    /**
     * validateMobileNumber() methods validate a mobileNumber using Regex
     * @param mobileNumber from ContactView class
     * @return mobileNumber
     */
    public static Boolean validateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("^\\d{10}$");
    }

    /**
     * validatePersonName() methods validate a personName using Regex
     * @param personName from ContactView class
     * @return personName
     */
    public static Boolean validatePersonName(final String personName) {
        return personName.matches("^[a-zA-Z\\s]{3,20}$");
    }

    /**
     * validateProfileId() methods validate a user profileId using Regex
     * @param profileId from MessageView class
     * @return profileID
     */
    public static Boolean validateProfileId(final String profileId) {
        return profileId.matches("\\d+");
    }

    /**
     * validateAuthenticationSelection() methods validate a user selecting options for Authentication portal using Regex.
     * @param selectAuthenticationOption from AuthenticationView class
     * @return selectAuthentication
     */
    public static Boolean validateAuthenticationSelection(final String selectAuthenticationOption) {
        return selectAuthenticationOption.matches("[123]");
    }

    /**
     * selectMessengerOption() methods validate a user selecting option for Messenger portal using Regex
     * @param selectMessengerOption from MessengerView class
     * @return selectMessengerOption
     */
    public static Boolean validateMessengerPortal(final String selectMessengerOption) {
        return selectMessengerOption.matches("[1-4]");
    }
}