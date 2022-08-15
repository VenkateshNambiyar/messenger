package in.messenger.message.view;

import in.messenger.message.controller.MessengerController;
import org.apache.log4j.Logger;
import in.messenger.exception.UsernameAlreadyExistsException;
import in.messenger.message.model.Message;
import in.messenger.userInput.UserInput;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * provide a message Details to user
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class MessageView {
    private static final Logger LOGGER = Logger.getLogger("MessageView.class");
    private static final MessengerController MESSENGER_CONTROLLER = new MessengerController();

    /**
     * sendMessage() methods get a message from user
     */
    public String assignMessage() {
        LOGGER.info("Enter Your textMessage");

        return UserInput.getString();
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
    public void sendMessage() {
        final Message message = new Message();

        message.setMessageTime(assignTime());
        message.setMessageDetails(assignMessage());
        try {

            if(MESSENGER_CONTROLLER.sendMessage(message)) {
                LOGGER.info("Message Send Successfully");
            } else {
                LOGGER.info("Try again");
            }
        } catch (UsernameAlreadyExistsException exception) {
            LOGGER.warn("username already Exists");
        }
    }

    /**
     * messageHistory() methods retrieve a message from database.
     */
    public void messageHistory() {
        final Message message = new Message();

        try {
            final List<Message> sendMessageHistory = MESSENGER_CONTROLLER.messageHistory(message.getUsername());

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