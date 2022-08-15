package in.messenger.message.service;

import in.messenger.message.dao.ContactDao;
import in.messenger.message.dao.MessageDao;
import in.messenger.message.model.Contact;
import in.messenger.message.model.Message;
import java.util.List;

/**
 * implements a Messenger interface
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class ServiceImplementation implements Service {

	private static final ContactDao CONTACT_DAO = new ContactDao();
	private static final MessageDao MESSAGE_DAO = new MessageDao();

    /**
     * to represent a display a user contact retrieve from database
     * @param username represent a user request
     * @return return List<Contact> to view class
     */
    public List<Contact> displayContact(final String username) {
        return CONTACT_DAO.displayContact(username);
    }

    /**
     * to add a new contact in database
     * @param contact represent a Contact class object instance
     * @return a boolean to Message View
     */
    public boolean addContact(final Contact contact) {
        return CONTACT_DAO.addContact(contact);
    }

    /**
     * to send a message to user and stare in database
     * @param message represent a Message class object instance
     * @return boolean to Message view
     */
    public boolean messageDetails(final Message message) {
        return MESSAGE_DAO.sendMessage(message);
    }

    /**
     * to retrieve a message history of the user
     * @param username represent an user request
     * @return a List<Message> to Message view
     */
    public List<Message> sendMessageHistory(final String username) {
        return MESSAGE_DAO.displayMessage(username);
    }
}