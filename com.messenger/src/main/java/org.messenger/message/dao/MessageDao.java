package org.messenger.message.dao;


import org.authentication.authentication.connectDatabase.ConnectDataBase;
import org.authentication.authentication.exception.UserNotFoundException;
import org.messenger.message.model.Contact;
import org.messenger.message.model.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * MessageDao class connecting Database then store and retrieve data from Database.
 */
public class MessageDao {

    /**
     * retrieveMessageId() method retrieve a messageId from Database
     * @param message represent a Message class object instance
     */
    private static void retrieveMessageId(final Message message) {
        final String selectSql = "select org_id from message where message_time = ?";

        try (final PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(selectSql)) {
            preparedStatement.setTimestamp(1, message.getMessageTime());
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message.setMessage_id(resultSet.getLong("org_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * profileId() to retrieveProfileId from DataBase
     * @param message represent a Messege class object instance
     * @param contact represent a Contact class object instance
     * @return a boolean value
     */
    public static boolean profileId(final Message message, final Contact contact) {
        ContactDao.retrieveUserId(contact);

        try {
            final String selectSql = "select contact_id from user_contact where contact_id = ? and user_id = ? ";

            try (final PreparedStatement preparedStatement =
                         ConnectDataBase.getConnection().prepareStatement(selectSql)) {
                preparedStatement.setLong(2, contact.getRetrieveUserId());
                preparedStatement.setLong(1, message.getProfile_Id());
                final ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    contact.setRetrieveContactId(resultSet.getLong("contact_id"));
                }
                return validateProfileId(contact, message);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     *  validateProfileId() method validate given user profileID;
     * @param contact represent a Contact class object instance
     * @param message represent a Message class object instance
     * @return
     */
    public static boolean validateProfileId(final Contact contact, final Message message) {
        if (contact.getRetrieveContactId().equals(message.getProfile_Id())) {
            return true;
        } else {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     *  sendMessageDetails() store a message into database.
     * @param message represent a Messenger class object instance
     */
    private static void sendMessageDetails(final Message message) {
        final String insertSql = "insert into message(message_time, message_details) values(?, ?)";

        try (final PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(insertSql)) {
            preparedStatement.setTimestamp(1, message.getMessageTime());
            preparedStatement.setString(2, message.getMessageDetails());
            preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * sendMessage() to send message and store that information into database
     * @param message represent a Message class object instance
     * @param contact represent a Contact class object instance
     * @return a boolean value
     */
    public static boolean sendMessage(final Message message, final Contact contact) {
        sendMessageDetails(message);

        retrieveMessageId(message);

        final String insertSql = "insert into user_message(user_id, message_id, contact_id) values (?, ?, ?)";

        try (final PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(insertSql)) {
            preparedStatement.setLong(1, contact.getRetrieveUserId());
            preparedStatement.setLong(2, message.getMessage_id());
            preparedStatement.setLong(3, message.getProfile_Id());
            preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }

        return true;
    }

    /**
     * sendMessageHistory() methods store send message Details in DataBase.
     * @param contact represent a Contact class object instance
     * @return contactId
     */
    public static List<Long> sendMessageHistory(final Contact contact) {
        ContactDao.retrieveUserId(contact);

        final List<Long> contactId = new ArrayList<>();
        final String selectSql = "Select contact_id from user_contact where user_id = ?";

        try (final PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(selectSql)) {
            preparedStatement.setLong(1, contact.getRetrieveUserId());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contactId.add(resultSet.getLong("contact_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }

        return contactId;
    }

    /**
     * messageHistory() methods a retrieve a message from database
     * @param contact represent a Contact class object instance
     * @return retrieveMessageId from DataBase
     */
    public static List<Long> messageHistory(final Contact contact) {
        final List<Long> retrieveContactId = sendMessageHistory(contact);

        final List<Long> retrieveMessageId = new ArrayList<>();
        final Iterator<Long> iterator = retrieveContactId.iterator();

        try {
            while (iterator.hasNext()) {
                contact.setRetrieveContactId (iterator.next());
                final String messageHistory = "Select message_id from user_message where user_id =? and contact_id = ?";

                try (final PreparedStatement preparedStatement =
                             ConnectDataBase.getConnection().prepareStatement(messageHistory)) {
                    preparedStatement.setLong(1, contact.getRetrieveUserId());
                    preparedStatement.setLong(2, contact.getRetrieveContactId());

                    final ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        retrieveMessageId.add(resultSet.getLong("message_id"));
                    }
                }
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }

        return retrieveMessageId;
    }

    /**
     * displayMessage() display a user send message from Database
    * @param contact represent a Contact class object instance
     * @retun List<Message></Message>
     */
    public static List<Message> displayMessage(final Message message, final Contact contact) {
        final List<Message> displayMessageDetails = new ArrayList<>();
        final List<Long> retrieveMessage = messageHistory(contact);

        for (Long retrieveMessageDetail : retrieveMessage) {
            message.setMessage_id(retrieveMessageDetail);

            final String selectSql = "Select message_time, message_details  from message where org_id = ?";

            try (final PreparedStatement preparedStatement =
                         ConnectDataBase.getConnection().prepareStatement(selectSql)) {
                preparedStatement.setLong(1, message.getMessage_id());

                final ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    message.setMessageTime(resultSet.getTimestamp("message_time"));
                    message.setMessageDetails(resultSet.getString("message_details"));
                    displayMessageDetails.add(message);
                }
            } catch (Exception exception) {
                throw new UserNotFoundException("UserNotFound");
            }
        }

        return displayMessageDetails;
    }
}