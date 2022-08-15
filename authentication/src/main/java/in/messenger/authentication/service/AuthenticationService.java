package in.messenger.authentication.service;

import in.messenger.authentication.model.Authentication;

import java.util.List;
import java.util.Map;

/**
 * provide an interface for Authentication in Messenger.
 *
 * @version 1.0
 * @author Venkatesh N
 */
public interface AuthenticationService {

  List<Authentication> userDetails(final String username);

  boolean addUser(final Authentication authentication);

  boolean updatePassword(final Authentication authentication);

  List<Authentication> getAllDetails();

  boolean deleteUser(final String username);
}