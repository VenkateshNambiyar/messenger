package in.messenger.authentication.dao;

import in.messenger.authentication.model.Authentication;
import in.messenger.connectDatabase.ConnectDataBase;
import in.messenger.exception.UserNotFoundException;
import in.messenger.exception.UsernameAlreadyExistsException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * To insert and retrieve data from database
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class AuthenticationDao {

    /**
     * To insert a user information into DataBase.
     * @param authentication represents an Authentication class object
     */
    public boolean addUser(final Authentication authentication) {
        final String insertSql = "insert into user_login (user_name, password) values(?, ?)";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(insertSql)) {

            preparedStatement.setString(1, authentication.getUsername());
            preparedStatement.setString(2, authentication.getPassword());

            return preparedStatement.execute();
        } catch (Exception sqlException) {
            throw new UsernameAlreadyExistsException("username already Exists");
        }
    }

    /**
     * It retrieves a userName from database
     * @param username represent an user request
     * @return boolean
     */
    public List<Authentication> userDetails(final String username) {
        final List<Authentication> userDetails = new ArrayList<>();
        final String selectSql = " Select org_id, user_name from user_login where user_name = ?";

        try (PreparedStatement preparedStatement = ConnectDataBase.getInstance().getConnection().prepareStatement(selectSql)) {

            preparedStatement.setString(1, username);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Authentication authentication = new Authentication();

                authentication.setUserId(resultSet.getLong("org_id"));
                authentication.setUsername(resultSet.getString("user_name"));

                userDetails.add(authentication);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
        return userDetails;
    }

    /**
     * To get all userDetails in DataBase
     */
    public List<Authentication> getAllDetails() {
        final List<Authentication> allUserDetails = new ArrayList<>();
        final String selectSql = " Select org_id, user_name from user_login ";

        try( PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(selectSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                final Authentication authentication = new Authentication();

                authentication.setUserId(resultSet.getLong("org_id"));
                authentication.setUsername(resultSet.getString("user_name"));

                allUserDetails.add(authentication);
            }
        } catch (Exception exception) {
            throw new UserNotFoundException("UserNotFound");
        }
        return allUserDetails;
    }

    /**
     * To change a password in Database.
     * @param authentication represent an Authentication class Object
     */
    public boolean updatePassword(final Authentication authentication) {
        final String updateSql = "update user_login SET password = ? where user_name = ?";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(updateSql)) {

            preparedStatement.setString(1, authentication.getPassword());
            preparedStatement.setString(2, authentication.getUsername());

            return preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }

    /**
     * to delete a account in database
     * @param username represent an user request
     */
    public boolean deleteUser(final String username) {
        final String deleteSql = "delete from user_login where user_name = ?";

        try (PreparedStatement preparedStatement =
                     ConnectDataBase.getInstance().getConnection().prepareStatement(deleteSql)) {

            preparedStatement.setString(1, username);

            return preparedStatement.execute();
        } catch (Exception exception) {
            throw new UserNotFoundException("User Not Found");
        }
    }
}