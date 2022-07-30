package org.messenger.message.view;


import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import org.messenger.message.model.Contact;
import org.messenger.message.model.Message;
import org.messenger.message.controller.MessageController;
import org.authentication.authentication.validation.Validation;
import org.authentication.authentication.exception.UserNotFoundException;
import org.authentication.authentication.exception.UsernameAlreadyExistsException;


/**
 * MessageView class provide a message Details to user
 */
public class MessageView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = Logger.getLogger("MessageView.class");

    /**
     * assignProfileId() methods a get a profileId from user to send message
     */
    public Long assignProfileId() {
        LOGGER.info("Enter userProfileId : ");
        final Long profileId = SCANNER.nextLong();

        if (!Validation.validateProfileId(String.valueOf(profileId))) {
            return assignProfileId();
        }

        return profileId;
    }


    /**
     * sendMessage() methods get a message from user
     */
    public String assignMessage() {
        final Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter Your textMessage");

        return scanner.nextLine();
    }

    /**
     *validateProfileId() method to validate with contact_id
     */
    public void validateProfileId() {
        final Contact contact = Contact.getInstance();
        final Message message = Message.getInstance();

        message.setProfile_Id(assignProfileId());

        try {
            if(MessageController.profileId(message, contact)) {
                LOGGER.info("Now,You can start your conversion");
                sendMessage(message, contact);
            } else {
                LOGGER.info("Try again");
            }
        } catch (UserNotFoundException exception) {
            LOGGER.warn("userNotFound");

            validateProfileId();
        }
    }

    /**
     * displayTimeStamp() methods a display TimeStamp.
     */
    private Timestamp assignTime() {
        final Date date = new Date();
        final long time = date.getTime();

        return new Timestamp(time);
    }

    /**
     * message() methods to send a message.
     */
    public void sendMessage(final Message message, final  Contact contact) {
        message.setMessageTime(assignTime());
        message.setMessageDetails(assignMessage());

        try {
            if(MessageController.sendMessage(contact, message)) {
                LOGGER.info("Message Send Successfully");
            } else {
                LOGGER.info("Try again");
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username already Exists");

            validateProfileId();
        }
    }

    /**
     * messageHistory() methods retrieve a message from database.
     */
    public void messageHistory() {
        final Contact contact = Contact.getInstance();
        final Message message = Message.getInstance();

        try {
            List<Message> sendMessageHistory = MessageController.messageHistory(message, contact);

            System.out.println("Date"+"\t\t\t" + "Time" + "\t\t" + "MessageHistory");

            for (Message value : sendMessageHistory) {
                System.out.println(value);
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username Not Found Exists");

            messageHistory();
        }
    }
}