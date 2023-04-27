package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static final String INIT_DB_FILENAME = "sql/init_db.sql";

    public static void main(String[] args) throws SQLException {
        Connection connection = Database.getInstance().getConnection();
        try (Statement statement = connection.createStatement()){
            String sql = Files.readString(Path.of(INIT_DB_FILENAME));
            statement.executeUpdate(sql);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }
}
