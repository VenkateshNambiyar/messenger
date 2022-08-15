package in.messenger.authentication.service;

import in.messenger.authentication.dao.AuthenticationDao;
import in.messenger.authentication.model.Authentication;
import java.util.List;

/**
 * implements a Messenger Authentication services methods
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class AuthenticationServiceImplementation implements AuthenticationService {
    private static final AuthenticationDao AUTHENTICATION_DAO = new AuthenticationDao();

    /**
     * provides a  user to log_in their account
     */
    @Override
    public List<Authentication> userDetails(final String username) {
        return AUTHENTICATION_DAO.userDetails(username);
    }

    /**
     * provides a user to create a new Account
     */
    @Override
    public boolean addUser(final Authentication authentication) {
        return AUTHENTICATION_DAO.addUser(authentication);
    }

    /**
     * provides user to change a user password
     */
    @Override
     public boolean updatePassword(final Authentication authentication) {
        return AUTHENTICATION_DAO.updatePassword(authentication);
     }

    /**
     * provide all userDetails to client
     * @return message to Controller
     */
    @Override
    public List<Authentication> getAllDetails() {
        return AUTHENTICATION_DAO.getAllDetails();
    }

    /**
     * to delete a particular userDetails
     * @param username represent a user request
     * @return message to controller
     */
    @Override
    public boolean deleteUser(final String username) {
        return AUTHENTICATION_DAO.deleteUser(username);
    }
}