package org.authentication.authentication.dao;


import org.authentication.authentication.connectDatabase.ConnectDataBase;
import org.authentication.authentication.exception.UsernameAlreadyExistsException;
import org.authentication.authentication.exception.UserNotFoundException;
import org.authentication.authentication.model.Authentication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * AuthenticationDao class insert and retrieve data from database
 */
public class AuthenticationDao {

    /**
     * addUser() to insert a user information into DataBase.
     * @param authentication represents an Authentication class object
     */
    public static boolean addUser(final Authentication authentication) {
        final String insertSql = "insert into user_login (user_name, password) values(?, ?)";

        try (final PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(insertSql)) {
            preparedStatement.setString(1, authentication.getUsername());
            preparedStatement.setString(2, authentication.getPassword());
            return preparedStatement.execute();
        } catch (Exception sqlException) {
            throw new UsernameAlreadyExistsException("username already Exists");
        }
    }

    /**
     * retrieveUserName() it retrieves a userName from database
     * @param authentication represent an Authentication object
     */
    private static void retrieveUserName(final Authentication authentication) {
        final String selectSql = " Select user_name, password from user_login where user_name = ?";

        try (PreparedStatement preparedStatement = ConnectDataBase.getConnection().prepareStatement(selectSql)) {
            preparedStatement.setString(1, authentication.getUsername());
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                authentication.setRetrieveUsername(resultSet.getString("user_name"));
                authentication.setRetrievePassword(resultSet.getString("password"));
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    /**
     * login() methods get a login details from database
     * @param authentication represent an Authentication class object
     */
    public static boolean login(final Authentication authentication) {
        try {
            retrieveUserName(authentication);

            return (authentication.getUsername().equals(authentication.getRetrieveUsername()))
                    & (authentication.getPassword().equals(authentication.getRetrievePassword()));
        } catch (Exception exception) {
            throw new UserNotFoundException("Username not found");
        }
    }

    /**
     * validateUsername() validate a user information using retrieve data from Database
     * @param authentication represent an Authentication class object
     * @return true
     */
    public static Boolean validateUsername(final Authentication authentication) {
        retrieveUserName(authentication);

        return authentication.getUsername().equals(authentication.getRetrieveUsername());
    }

    /**
     * updatePassword() its change a password in Database.
     * @param authentication represent an Authentication class Object
     */
    public static boolean updatePassword(final Authentication authentication) {
        final String updateSql = "update user_login SET password = ? where user_name = ?";

        try (final PreparedStatement preparedStatement =
                     ConnectDataBase.getConnection().prepareStatement(updateSql)) {
            preparedStatement.setString(1,authentication.getPassword());
            preparedStatement.setString(2,authentication.getUsername());
            return preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }
}