package eu.senla.shabalin;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnector {
    private static Connection connection;
    private static Properties property;
    private static String url;
    private static String user;
    private static String password;

    public static Connection getConnection() throws ClassNotFoundException {
        if (connection == null) {
            String pathToThePropertyFile;
            try {
                if (System.getProperty("os.name").equals("Linux")) {
                    pathToThePropertyFile = "src/main/resources/bd.properties";
                } else {
                    pathToThePropertyFile = "src\\main\\resources\\bd.properties";
                }
                property = new Properties();
                property.load(new FileInputStream(pathToThePropertyFile));
            } catch (IOException e) {
                System.err.println("Property file don't found!");
            }
            url = property.getProperty("url");
            user = property.getProperty("user");
            password = property.getProperty("password");

            Class.forName("org.postgresql.Driver");

            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.err.println("Не удалось поключиться к БД!");
            }
            System.out.println("Подключение к базе установлено!");
        }
        return connection;
    }
}
