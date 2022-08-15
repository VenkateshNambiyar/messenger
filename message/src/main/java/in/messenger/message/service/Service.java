package in.messenger.message.service;

import in.messenger.message.model.Contact;
import in.messenger.message.model.Message;
import java.util.List;

/**
 * provides have a list of messenger Services
 *
 * @version 1.0
 * @author Venkatesh N
 */
public interface Service {
    List<Contact> displayContact(final String username);

    boolean addContact(final Contact contact);

    boolean messageDetails(final Message message);

    List<Message> sendMessageHistory(final String username);
}