package in.messenger.authentication.controller;

import in.messenger.authentication.model.Authentication;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * provide a  Rest interface
 *
 * @version 1.0
 * @author Venkatesh N
 */
public interface AuthenticationRestController {

    List<Authentication> getUserDetails(final @PathParam("username") String username);

    String addNewUser(final Authentication authentication);

    List<Authentication> getAllDetails();

    String updatePassword(final Authentication authentication);

    String deleteUserDetails(final @PathParam("username") String username);
}