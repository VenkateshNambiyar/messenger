package in.messenger.message.dao;

import in.messenger.connectDatabase.ConnectDataBase;
import in.messenger.exception.UserNotFoundException;
import in.messenger.exception.UsernameAlreadyExistsException;
import in.messenger.message.model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * connecting Database then store and retrieve data from Database.
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class ContactDao {

    /**
     * to store contact details information into data* @param personName from controller
     * @param contact represent a Contact class object instance
     * @return a boolean value
     */
    public boolean addContact(final Contact contact) {
        final String insertSql = "insert into contact (mobile_number, person_name) values(?, ?)";

        try (PreparedStatement preparedStatement = ConnectDataBase.getInstance().getConnection().prepareStatement(insertSql)) {

            preparedStatement.setLong(1, contact.getMobileNumber());
            preparedStatement.setString(2, contact.getPersonName());
            preparedStatement.execute();

            createContact(contact);
        } catch (Exception exception) {
            throw new UsernameAlreadyExistsException("username Already Exists");
        }
        return true;
    }

    /**
     * create a new contact for user
     * @param contact represent a Contact class object instance
     */
    private void createContact(final Contact contact) {
        retrieveUserId(contact);

        retrieveContactId(contact);

        final String insertSql = "insert into user_contact(user_id, contact_id) values (?,?)";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(insertSql)) {

            preparedStatement.setLong(1, contact.getRetrieveUserId());
            preparedStatement.setLong(2, contact.getRetrieveContactId());
            preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * retrieveContactId retrieve a contactId from Database
     * @param contact represent a Contact class object instance
     */
    public void retrieveContactId(final Contact contact) {
        final String retrieve_contact_id = "select org_id from contact where mobile_number = ?";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(retrieve_contact_id)) {

            preparedStatement.setLong(1, contact.getMobileNumber());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contact.setRetrieveContactId(resultSet.getLong("org_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    /**
     * retrieveUserId retrieveUserId from DataBase
     *  @param contact represent a Contact class object instance
     */
    public void retrieveUserId(final Contact contact) {
        final String selectSql = " Select org_id from user_login where user_name = ?";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(selectSql)) {

            preparedStatement.setString(1, contact.getUsername());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contact.setRetrieveUserId(resultSet.getLong("org_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    /**
     * provide a user contact details
     * @param username represent an user request
     * @return user contact details
     */
    public List<Contact> displayContact(final String username) {
        final List<Contact> displayContact = new ArrayList<>();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("select contact.mobile_number, contact.person_name from contact inner join user_contact");
        stringBuilder.append(" on user_contact.contact_id = contact.org_id inner join user_login on");
        stringBuilder.append(" user_login.org_id = user_contact.user_id where user_login.user_name = '?' ");
        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(String.valueOf(stringBuilder))) {

            preparedStatement.setString(1, username);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Contact contact = new Contact();

                contact.setMobileNumber(resultSet.getLong("mobile_number"));
                contact.setPersonName(resultSet.getString("person_name"));

                displayContact.add(contact);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
        return displayContact;
    }
}