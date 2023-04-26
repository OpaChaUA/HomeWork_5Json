package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Database {
    private static final Database DATABASE = new Database();
    private Connection connection;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("URL");
    private static final String JDBC_URL = "jdbcURL";

    public Database() {
        try {
            connection = DriverManager.getConnection(resourceBundle.getString(JDBC_URL));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Database getInstance(){
        return DATABASE;
    }
    public Connection getConnection(){
        return connection;
    }
}