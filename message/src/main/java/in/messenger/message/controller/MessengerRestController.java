package in.messenger.message.controller;

import in.messenger.message.model.Contact;
import in.messenger.message.model.Message;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * provides a RestController\
 *
 * @version 1.0
 * @author Venkatesh N
 */
public interface MessengerRestController {
    String addNewContact(final Contact contact);

    List<Contact> getContactDetails(final @PathParam("userContact") String username);

    String sendUserMessage(final Message message);

    List<Message> getMessageDetails(final @PathParam("userMessage") String username);
}