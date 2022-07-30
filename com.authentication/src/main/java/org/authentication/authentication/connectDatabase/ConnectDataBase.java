package org.authentication.authentication.connectDatabase;


import org.authentication.authentication.exception.DatabaseNotConnectedException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


/**
 * ConnectDatabase class provide a Connection to Database using properties files
 */
public class ConnectDataBase {
    private static final Properties PROPERTIES = new Properties();
    public static Connection connection = null;

    /**
     * getConnection() methods provides a  database connection.
     * @return connection
     */
    public static Connection getConnection() {
        try (final FileInputStream fileInputStream = new FileInputStream
                ("C:/Users/venka/Documents/Task/messenger/propertiesFile/DatabaseDetails.properties")) {
            PROPERTIES.load(fileInputStream);

            connection = DriverManager.getConnection(PROPERTIES.getProperty("url"),
                    PROPERTIES.getProperty("username"), PROPERTIES.getProperty("password"));

        } catch (Exception exception) {
            throw new DatabaseNotConnectedException("DataBase Not Connected");
        }

        return connection;
    }
}