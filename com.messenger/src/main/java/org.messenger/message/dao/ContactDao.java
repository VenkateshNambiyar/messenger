package org.messenger.message.dao;


import org.authentication.authentication.connectDatabase.ConnectDataBase;
import org.authentication.authentication.exception.UserNotFoundException;
import org.authentication.authentication.exception.UsernameAlreadyExistsException;
import org.messenger.message.model.Contact;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * ContactDao class connecting Database then store and retrieve data from Database.
 */
public class ContactDao {

    /**
     * retrieveContact() methods retrieve contactId from Database
     * @param contact represent a Contact class object instance
     * @return a List<Long>
     */
    public List<Long> retrieveContact(final Contact contact) {
        retrieveUserId(contact);

        final List<Long> getContact = new ArrayList<>();
        final String selectSql = "Select contact_id from user_contact where user_id = ?";

        try (PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(selectSql)) {
            preparedStatement.setLong(1, contact.getRetrieveUserId());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                getContact.add(resultSet.getLong("contact_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }

        return getContact;
    }

    /**
     * displayContact() methods retrieve contact from dataBase.
     //@param contact represent a Contact class object instance
     * @return List<Contact>
     */
    public List<Contact> displayContact(final Contact contact) {
        final List<Long> displayUserContact = retrieveContact(contact);

        final List<Contact> contactList = new ArrayList<>();

        for (Long contactId : displayUserContact) {
            final String selectSql = "select org_id, mobile_number, person_name from contact where org_id = ?";

            try (PreparedStatement preparedStatement =
                         ConnectDataBase.getConnection().prepareStatement(selectSql)) {
                preparedStatement.setLong(1, contactId);
                final ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    contact.setContactId(resultSet.getLong("org_id"));
                    contact.setRetrieveMobileNumber(resultSet.getLong("mobile_number"));
                    contact.setRetrievePersonName(resultSet.getString("person_name"));
                    contactList.add(contact);
                }
            } catch (Exception exception) {
                throw new UserNotFoundException("UserNotFound");
            }
        }

        return contactList;
    }

    /**
     * addContact() methods to store contact details information into data* @param personName from controller
     * @param contact represent a Contact class object instance
     * @return a boolean value
     */
    public boolean addContact(final Contact contact) {
        final String insertSql = "insert into contact (mobile_number, person_name) values(?, ?)";

        try (PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(insertSql)) {
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
     * createContact() create a new contact for user
     * @param contact represent a Contact class object instance
     */
    private void createContact(final Contact contact) {
        ContactDao.retrieveUserId(contact);

        retrieveContactId(contact);

        final String insertSql = "insert into user_contact(user_id, contact_id) values (?,?)";

        try (PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(insertSql)) {
            preparedStatement.setLong(1, contact.getRetrieveUserId());
            preparedStatement.setLong(2, contact.getRetrieveContactId());
            preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
    }

    /**
     * retrieveContactId retrieve a contactId from Databasae
     * @param contact represent a Contact class object instance
     */
    public void retrieveContactId(final Contact contact) {
        final String retrieve_contact_id = "select org_id from contact where mobile_number = ?";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getConnection().prepareStatement(retrieve_contact_id)) {

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

        try (PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(selectSql)) {
            preparedStatement.setString(1, contact.getAccessIdName());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                contact.setRetrieveUserId(resultSet.getLong("org_id"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }
}