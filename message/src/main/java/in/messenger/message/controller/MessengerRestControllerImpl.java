package in.messenger.message.controller;

import in.messenger.message.model.Contact;
import in.messenger.message.model.Message;
import javax.ws.rs.*;
import java.util.List;

/**
 * Implement a REST controller in messenger
 *
 * @version 1.0
 * @author Venkatesh N
 */
@Path("/")
public class MessengerRestControllerImpl extends MessengerController implements MessengerRestController {

    /**
     *to add a new contact
     * @param contact represent a user request
     * @return message to the user
     */
    @Override
    @Path("/addContact")
    @Produces("application/json")
    @POST
    public String addNewContact(final Contact contact) {
        return super.addContact(contact) ? "{\tSuccessfully Contact added\t}" : "{\tNot Contact added\t}";
    }

    /**
     * provide to get a particular user contact details
     * @param username represent an user request
     * @return userContactDetails
     */
    @Override
    @Path("/{userContact}")
    @Produces("application/json")
    @GET
    public List<Contact> getContactDetails(final @PathParam("userContact") String username) {
        return super.displayContact(username);
    }

    /**
     * provide to send a message to user
     * @param message represent an user request
     * @return message to the user
     */
    @Override
    @Path("/sendMessage")
    @Produces("application/json")
    @POST
    public String sendUserMessage(final Message message) {
        return super.sendMessage(message) ? "{\tSuccessfully Message Send\t}" : "{\tNot Send\t}";
    }

    /**
     * provide to get a particular user message details
     * @param username represent a user request
     * @return user Message details to the user
     */
    @Override
    @Path("/{userMessage}")
    @Produces("application/json")
    @GET
    public List<Message> getMessageDetails(final @PathParam("userMessage") String username) {
        return super.messageHistory(username);
    }
}