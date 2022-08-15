package org.messenger.message.service;


import org.messenger.message.dao.ContactDao;
import org.messenger.message.dao.MessageDao;
import org.messenger.message.model.Contact;
import org.messenger.message.model.Message;
import java.util.List;

/**
 * ServiceImplementation class implements a Messenger abstract methods
 */
public class ServiceImplementation implements Service {

	private static final ContactDao CONTACT_DAO = new ContactDao();
	private static final MessageDao MESSAGE_DAO = new MessageDao();

    /**
     * displayContact() method represent a display a user contact retrieve from database
     * @param contact represent a Contact class object instance
     * @return return List<Contact> to view class
     */
    public List<Contact> displayContact(final Contact contact) {
        return CONTACT_DAO.displayContact(contact);
    }

    /**
     * addContact() method to add a new contact in database
     * @param contact represent a Contact class object instance
     * @return a boolean to Message View
     */
    public boolean addContact(final Contact contact) {
        return CONTACT_DAO.addContact(contact);
    }

    /**
     * profileId() to validate profileId.
     * @param message represent a Contact class object instance
     * @param contact represent a Contact class object instance
     * @return a boolean to Message view
     */
    public boolean profileId(final Message message, final Contact contact) {
        return MESSAGE_DAO.profileId(message, contact);
    }

    /**
     * messageDetails() methods to send a message to user and stare in database
     * @param contact represent a Contact class object instance
     * @param message represent a Message class object instance
     * @return boolean to Message view
     */
    public boolean messageDetails(final Contact contact, final Message message) {
        return MESSAGE_DAO.sendMessage(message, contact);
    }

    /**
     * sendMessageHistory() to retrieve a message history of the user
     * @param message represent a Message class object instance
     * @param contact represent a Contact class object instance
     * @return a List<Message> to Message view
     */
    public List<Message> sendMessageHistory(final Message message, final Contact contact) {
        return MESSAGE_DAO.displayMessage(message, contact);
    }
}