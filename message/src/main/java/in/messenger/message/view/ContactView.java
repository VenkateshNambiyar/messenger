package in.messenger.message.view;

import in.messenger.message.controller.MessengerController;
import org.apache.log4j.Logger;
import in.messenger.exception.UsernameAlreadyExistsException;
import in.messenger.message.model.Contact;
import in.messenger.userInput.UserInput;
import in.messenger.validation.Validation;
import java.util.List;

/**
 * provides a contact details of the user.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class ContactView {
    private static final Logger LOGGER = Logger.getLogger(ContactView.class);
    private static final MessengerController MESSENGER_CONTROLLER = new MessengerController();

    /**
     * to get a mobilNumber from the user.
     */
    public long assignMobileNumber() {
        LOGGER.info("Enter Mobile Number :");
        final Long mobileNumber = UserInput.getLong();

        if (!Validation.validateMobileNumber(String.valueOf(mobileNumber))) {
            return assignMobileNumber();
        }
        return mobileNumber;
    }

    /**
     * to assign a personName.
     */
    private String assignPersonName() {
        LOGGER.info("Enter Person Name :");
        final String personName = UserInput.getString();

        if (!Validation.validatePersonName(personName)) {
            return assignPersonName();
        }
        return personName;
    }

    /**
     * to display a user contact
     */
    public void displayContact() {
        final Contact contact = new Contact();

        contact.setMobileNumber(assignMobileNumber());
        contact.setPersonName(assignPersonName());

        try {
            final List<Contact> displayContact = MESSENGER_CONTROLLER.displayContact(contact.getUsername());

            System.out.println("profileId" + "\t" + "MobileNumber" + "\t" + "Name");

            for (Contact userContact : displayContact) {
                System.out.println(userContact);
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username Not Found Exists");
            displayContact();
        }
    }

    /**
     * creates a new Contact to users
     */
    public void createContact() {
        final Contact contact = new Contact();

        contact.setMobileNumber(assignMobileNumber());
        contact.setPersonName(assignPersonName());

        try {
            if (MESSENGER_CONTROLLER.addContact(contact)) {
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
     * to validate a messenger selection option
     *
     * @return selectMessengerOption
     */
    public String selectMessengerPortal() {
        LOGGER.info("Select Anyone Operation: 1)DisplayContact \t 2)CreateNewContact \t 3)Message\t 4)Message History");
        LOGGER.info("Select Option:");
        final String selectMessengerOption = UserInput.getString();

        if (!Validation.validateMessengerChoiceSelection(selectMessengerOption)) {
            return selectMessengerPortal();
        }

        return selectMessengerOption;
    }

    /**
     * provide option to user to choose different stages in Messenger.
     */
    public void selectMessenger() {
        final MessageView messageView = new MessageView();

        switch (selectMessengerPortal()) {
        case "1" -> displayContact();
        case "2" -> createContact();
        case "3" -> messageView.sendMessage();
        case "4" -> messageView.messageHistory();
        default -> LOGGER.warn("Invalid Selection");
        }
    }
}