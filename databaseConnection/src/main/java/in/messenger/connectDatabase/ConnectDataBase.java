package in.messenger.connectDatabase;

import in.messenger.exception.DatabaseNotConnectedException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * provide a Connection to Database using properties files
 *
 * @version 1.0
 * @author Venkatesh N
 */
public class ConnectDataBase {
    private static ConnectDataBase connectDataBase;

    private ConnectDataBase() {}

    public static ConnectDataBase getInstance() {

        if (connectDataBase == null) {
            connectDataBase = new ConnectDataBase();
        }
        return connectDataBase;
    }

    /**
     * provides a  database connection.
     * @return connection
     */
    public Connection getConnection() {
        try (InputStream inputStream =
                     this.getClass().getClassLoader().getResourceAsStream("DatabaseDetails.properties")) {
            final Properties PROPERTIES = new Properties();

            PROPERTIES.load(inputStream);
            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("username"),
                    PROPERTIES.getProperty("password"));
        } catch (Exception exception) {
            throw new DatabaseNotConnectedException("DataBase Not Connected");
        }
    }
}