package by.verishko.kefir.dao.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String url = resourceBundle.getString("db.url");
        String login = resourceBundle.getString("db.login");
        String pass = resourceBundle.getString("db.password");
//        String driver = resourceBundle.getString("db.driver");
//        String poolSize = resourceBundle.getString("db.poolSize");
        return DriverManager.getConnection(url, login, pass);
    }
}
