package org.authentication.authentication.model;


/**
 * Authentication class is an Encapsulation. Its declare a variables as private.
 * Its provide public get and set methods to access and update the value of a private variable
 */
public class Authentication {
    private String username;
    private String password;
    private String retrieveUsername;
    private String retrievePassword;
    private static Authentication authentication;

    /**
     * Authentication() create a private Constructor
     */
    private Authentication() {
        super();
    }

    /**
     * getInstance() method to create a singleton class
     * @return authentication
     */
    public static Authentication getInstance() {

        if(authentication == null) {
            authentication = new Authentication();
        }
            return authentication;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetrieveUsername() {
        return retrieveUsername;
    }

    public void setRetrieveUsername(String retrieveUsername) {
        this.retrieveUsername = retrieveUsername;
    }

    public String getRetrievePassword() {
        return retrievePassword;
    }

    public void setRetrievePassword(String retrievePassword) {
        this.retrievePassword = retrievePassword;
    }
}