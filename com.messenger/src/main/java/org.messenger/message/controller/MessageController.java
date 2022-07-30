package org.messenger.message.controller;


import org.messenger.message.model.Contact;
import org.messenger.message.model.Message;
import org.messenger.message.service.ServiceImplementation;
import java.util.List;


/**
 * MessageController class controls the data flow into model object and updates the view whenever data changes.
 */
public class MessageController {
    private static final ServiceImplementation SERVICE_IMPLEMENTATION = new ServiceImplementation();

    /**
     * profileId() to validate a contact ProfileId for message
     * @param message represent a Message class object instance
     * @param contact represent a Contact class object instance
     * @return a boolean value if profileID is valid or not
     */
    public static boolean profileId(final Message message, final Contact contact) {
        return SERVICE_IMPLEMENTATION.profileId(message, contact);
    }

    /**
     * sendMessage() methods to store send message details into database.
     * @param contact represent a Contact class object instance
     * @param message represent a Message class object instance
     * @return a boolean value if message send or not
     */
    public static boolean sendMessage(final Contact contact, final Message message) {
        return SERVICE_IMPLEMENTATION.messageDetails(contact, message);
    }

    /**
     * messageHistory() methods to retrieve send message details from Database
     * @param message represent a Message class object instance
     * @param contact represent a Contact class object instance
     * @return List<Message> to view
     */
    public static List<Message> messageHistory(final Message message, final Contact contact) {
        return SERVICE_IMPLEMENTATION.sendMessageHistory(message, contact);
    }
}