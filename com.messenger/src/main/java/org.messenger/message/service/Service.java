package org.messenger.message.service;


import org.messenger.message.model.Contact;
import org.messenger.message.model.Message;
import java.util.List;


/**
 * Service Interface provides have a collection of abstract method for Messenger
 */
public interface Service {
    List<Contact> displayContact(final Contact contact);

    boolean addContact(final Contact contact);

    boolean messageDetails(final Contact contact, final Message message);

    List<Message> sendMessageHistory(final Message message, final Contact contact);
}