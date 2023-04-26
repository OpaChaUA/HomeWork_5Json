package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
    Connection connection = Database.getInstance().getConnection();
    }

}
