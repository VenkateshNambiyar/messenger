package in.messenger.message.controller;

import in.messenger.message.model.Contact;
import in.messenger.message.model.Message;
import in.messenger.message.service.ServiceImplementation;
import java.util.List;

/**
 * controls the data flow into model object and updates the view whenever data changes.
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class MessengerController {
    private static final ServiceImplementation SERVICE_IMPLEMENTATION = new ServiceImplementation();

    /**
     * to get a user contact information from database
     * @param username represent a user request.
     * @return a List<Contact> to view
     */
    public List<Contact> displayContact(final String username) {
        return SERVICE_IMPLEMENTATION.displayContact(username);
    }

    /**
     * to store user contact information into database
     * @param contact represent a Contact class instance
     * @return a boolean a value to view
     */
    public boolean addContact(final Contact contact) {
        return SERVICE_IMPLEMENTATION.addContact(contact);
    }

    /**
     * to store send message details into database.
     * @param message represent a Message class object instance
     * @return a boolean value if message send or not
     */
    public boolean sendMessage(final Message message) {
        return SERVICE_IMPLEMENTATION.messageDetails(message);
    }

    /**
     * to retrieve send message details from Database
     * @param username represent a user request
     * @return List<Message> to view
     */
    public List<Message> messageHistory(final String username) {
        return SERVICE_IMPLEMENTATION.sendMessageHistory(username);
    }
}