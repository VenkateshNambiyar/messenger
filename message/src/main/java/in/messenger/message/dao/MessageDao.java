package in.messenger.message.dao;

import in.messenger.connectDatabase.ConnectDataBase;
import in.messenger.exception.UserNotFoundException;
import in.messenger.message.model.Contact;
import in.messenger.message.model.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * connecting Database then store and retrieve data from Database.
 *
 * @author Venkatesh N
 * @version 1.0
 */
public class MessageDao {

    /**
     * to retrieve a messageId from Database
     *
     * @param message represent a Message class object instance
     */
    private void retrieveMessageId(final Message message) {
        final String selectSql = "select org_id from message where message_time = ?";

        try (PreparedStatement preparedStatement = ConnectDataBase.getInstance().getConnection().prepareStatement(selectSql)) {

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
     * provide a user id from the database
     * @param message represent an user request
     */
    private void retrieveUserId(final Message message) {
        final String selectSql = " Select org_id from user_login where user_name = ?";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(selectSql)) {

            preparedStatement.setString(1, message.getUsername());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                message.setUser_id(resultSet.getLong("org_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    /**
     * to retrieveProfileId from DataBase
     *
     * @param message represent a Message class object instance
     * @return boolean
     */
    public boolean profileId(final Message message) {
        retrieveUserId(message);

        try {
            final String selectSql = "select contact_id from user_contact where contact_id = ? and user_id = ? ";

            try (PreparedStatement preparedStatement =
                         ConnectDataBase.getInstance().getConnection().prepareStatement(selectSql)) {

                preparedStatement.setLong(2, message.getUser_id());
                preparedStatement.setLong(1, message.getProfile_Id());
                final ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    message.setProfile_Id(resultSet.getLong("contact_id"));
                }
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return true;
    }

    /**
     * to store a message into database.
     *
     * @param message represent a Messenger class object instance
     */
    private void sendMessageDetails(final Message message) {
        final String insertSql = "insert into message(message_time, message_details) values(?, ?)";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(insertSql)) {

            preparedStatement.setTimestamp(1, message.getMessageTime());
            preparedStatement.setString(2, message.getMessageDetails());
            preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * to send message and store that information into database
     *
     * @param message represent a Message class object instance
     * @return a boolean value
     */
    public boolean sendMessage(final Message message) {
        sendMessageDetails(message);

        retrieveMessageId(message);

        final String insertSql = "insert into user_message(user_id, message_id, contact_id) values (?, ?, ?)";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(insertSql)) {

            preparedStatement.setLong(1, message.getUser_id());
            preparedStatement.setLong(2, message.getMessage_id());
            preparedStatement.setLong(3, message.getProfile_Id());
            return preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * display a user send message from Database
     *
     * @param username represent an user request
     * @return List<Message>
     */
    public List<Message> displayMessage(final String username) {
        final List<Message> displayMessage = new ArrayList<>();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select contact.mobile_number, contact.person_name, message.message_time,");
        stringBuilder.append(" message.message_details from message inner join user_message on ");
        stringBuilder.append(" message.org_id = user_message.message_id inner join contact on ");
        stringBuilder.append(" user_message.contact_id = contact.org_id inner join user_login on");
        stringBuilder.append(" user_login.org_id = user_message.user_id");
        stringBuilder.append(" where  user_login.user_name = '?' ");

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(String.valueOf(stringBuilder))) {

            preparedStatement.setString(1, username);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Message message = new Message();
                final Contact contact = new Contact();

                contact.setMobileNumber(resultSet.getLong("mobile_number"));
                contact.setPersonName(resultSet.getString("person_name"));
                message.setMessageTime(resultSet.getTimestamp("message_time"));
                message.setMessageDetails(resultSet.getString("message_details"));

                displayMessage.add(contact);
                displayMessage.add(message);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return displayMessage;
    }
}