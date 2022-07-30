package org.messenger.message.view;


import org.apache.log4j.Logger;
import org.authentication.authentication.exception.UsernameAlreadyExistsException;
import org.authentication.authentication.model.Authentication;
import org.authentication.authentication.validation.Validation;
import org.messenger.message.controller.ContactController;
import org.messenger.message.model.Contact;
import java.util.List;
import java.util.Scanner;


/**
 * ContactView class provides a contact details of the user.
 */
public class ContactView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger(ContactView.class);

    /**
     * assignMobileNumber() methods get a mobilNumber from the user.
     */
    public Long assignMobileNumber() {
        LOGGER.info("Enter Mobile Number :");
        final Long mobileNumber = SCANNER.nextLong();

        if(!Validation.validateMobileNumber(String.valueOf(mobileNumber))) {
            return assignMobileNumber();
        }

        return mobileNumber;
    }

    /**
     * assignPersonName() methods assign a personName.
     */
    private String assignPersonName() {
        LOGGER.info("Enter Person Name :");
        final String personName = SCANNER.nextLine();

        if (!Validation.validatePersonName(personName)) {
            return assignPersonName();
        }

        return personName;
    }

    /**
     * addContact() methods to add a user contact details.
     * @return contact
     */
    public Contact addContact() {
        final Authentication authentication = Authentication.getInstance();
        final Contact contact = Contact.getInstance();

        contact.setAccessIdName(authentication.getUsername());
        return contact;
    }

    /**
     * displayContact() to display a user contact
     */
   public void displayContact() {
        final Contact contact = addContact();

        try {
             final List<Contact> displayContact = ContactController.displayContact(contact);

            System.out.println("profileId" + "\t" +  "MobileNumber" + "\t" + "Name");

            for (Contact userContact  : displayContact) {
                System.out.println(userContact);
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username Not Found Exists");

            displayContact();
        }
   }

    /**
     * createContact() creates a new Contact to users
     */
   public void createContact() {
        final Contact contact = addContact();

        contact.setMobileNumber(assignMobileNumber());
        contact.setPersonName(assignPersonName());

        try {
            if(ContactController.addContact(contact)) {
                LOGGER.info("Successfully New Contact created");
            } else {
                LOGGER.info("Try again");
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username already Exists");

            createContact();
        }
   }

    /**
     * selectMessengerPortal() to validate a messenger selection option
     * @return selectMessengerOption
     */
   public String selectMessengerPortal() {
        LOGGER.info("Select Anyone Operation: 1)DisplayContact \t 2)CreateNewContact \t 3)Message\t 4)Message History");
        LOGGER.info("Select Option:");
        final String selectMessengerOption = SCANNER.nextLine();

        if (!Validation.validateMessengerPortal(selectMessengerOption)) {
            return selectMessengerPortal();
        }

        return selectMessengerOption;
   }

    /**
     * selectMessage() method provide option to user to choose different stages in Messenger.
     */
   public void selectMessenger() {
       final MessageView messageView = new MessageView();

       switch (selectMessengerPortal()) {
       case "1" -> displayContact();
       case "2" -> createContact();
       case "3" -> messageView.validateProfileId();
       case "4" -> messageView.messageHistory();
       default -> LOGGER.warn("Invalid Selection");
       }
   }
}