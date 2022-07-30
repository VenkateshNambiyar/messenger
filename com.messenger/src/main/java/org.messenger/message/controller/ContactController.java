package org.messenger.message.controller;


import org.messenger.message.model.Contact;
import org.messenger.message.service.ServiceImplementation;
import java.util.List;


/**
 * ContactController class controls the data flow into model object and updates the view whenever data changes.
 */
public class ContactController {
    private static final ServiceImplementation SERVICE_IMPLEMENTATION = new ServiceImplementation();

    /**
     * displayContact() method gets a user contact information from database
     * @param contact represent a Contact class instance.
     * @return a List<Contact> to view
     */
    public static List<Contact> displayContact(final Contact contact) {
        return SERVICE_IMPLEMENTATION.displayContact(contact);
    }

    /**
     * addContact() method to store user contact information into database
     * @param contact represent a Contact class instance
     * @return a boolean a value to view
     */
    public static boolean addContact(final Contact contact) {
        return SERVICE_IMPLEMENTATION.addContact(contact);
    }
}