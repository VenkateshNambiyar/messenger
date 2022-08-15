package in.messenger.validation;

/**
 * Validate a user information
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class Validation {

    /**
     * To validate a username using Regex
     *
     * @param username from AuthenticationView class
     * @return validate username
     */
    public static boolean validateUsername(final String username) {
        return username.matches("^[a-zA-Z\\d\\s]{6,20}$");
    }

    /**
     * To validate a password using Regex
     *
     * @param password from AuthenticationView class
     * @return validate password
     */
    public static boolean validatePassword(final String password) {
        return password.matches("^(?=.*[\\d])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$");
    }

    /**
     * To validate a mobileNumber using Regex
     *
     * @param mobileNumber from ContactView class
     * @return validate mobileNumber
     */
    public static boolean validateMobileNumber(final String mobileNumber) {
        return mobileNumber.matches("^\\d{10}$");
    }

    /**
     * To validate a personName using Regex
     *
     * @param personName from ContactView class
     * @return validate personName
     */
    public static boolean validatePersonName(final String personName) {
        return personName.matches("^[a-zA-Z\\s]{3,20}$");
    }

    /**
     * To validate a user profileId using Regex
     *
     * @param profileId from MessageView class
     * @return validate profileID
     */
    public static boolean validateProfileId(final String profileId) {
        return profileId.matches("\\d+");
    }

    /**
     * To validate a user selecting options for Authentication portal using Regex.
     *
     * @param selectAuthenticationOption from AuthenticationView class
     * @return validate selectAuthentication
     */
    public static boolean validateAuthenticationChoiceSelection(final String selectAuthenticationOption) {
        return selectAuthenticationOption.matches("[123]");
    }

    /**
     * To validate a user selecting option for Messenger portal using Regex
     *
     * @param selectMessengerOption from MessengerView class
     * @return validate selectMessengerOption
     */
    public static boolean validateMessengerChoiceSelection(final String selectMessengerOption) {
        return selectMessengerOption.matches("[1-4]");
    }
}