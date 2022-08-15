package in.messenger.authentication.controller;

import in.messenger.authentication.model.Authentication;
import javax.ws.rs.*;
import java.util.List;

/**
 * Implement a RestController in Messenger
 */
@Path("/")
public class AuthenticationRestControllerImpl extends AuthenticationController implements AuthenticationRestController {

    /**
     * to get an individual userDetails
     * @param username represent a User Request
     * @return message Successfully or not
     */
    @Path("/{username}")
    @Produces("application/json")
    @GET
    public List<Authentication> getUserDetails(final @PathParam("username") String username) {
        return super.userDetails(username);
    }

    /**
     * to add a new User
     * @param authentication represent an user request
     * @return message is success or not
     */
    @Path("/addNewUser")
    @Produces("application/json")
    @POST
    public String addNewUser(final Authentication authentication) {
        return super.addUser(authentication) ? "{\n\tAdd Successfully\n\t}" : "{\n\tFail to add\n\t}";
    }

    /**
     * to get an all user details
     * @return all user details
     */
    @Path("/messenger")
    @Produces("application/json")
    @GET
    public List<Authentication> getAllDetails() {
        return super.getAllDetails();
    }

    /**
     * to update a user password
     * @param authentication represent an user request
     * @return message successfully or not
     */
    @Path("/changePassword")
    @Consumes("application/json")
    @PUT
    public String updatePassword(final Authentication authentication) {
        return super.changePassword(authentication) ? "{\n\tSuccessfully updated\n\t}" : "{\n\tNot Updated\n\t}";
    }

    /**
     * to delete a userDetails
     * @param username represent an User Request
     * @return message successfully or not
     */
    @Path("/delete/{username}")
    @Produces("application/json")
    @DELETE
    public String deleteUserDetails(final @PathParam("username") String username) {
        return super.deleteUser(username) ? "{\n\tDeleted Successfully\n\t}" : "{\n\tNot deleted\n\t}";
    }
}