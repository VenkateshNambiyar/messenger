package in.messenger.authentication.model;

/**
 * Its declare a variables as private. Its provide  get and set methods to access the private variable.
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class Authentication {
    private long userId;
    private String username;
    private String password;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
}