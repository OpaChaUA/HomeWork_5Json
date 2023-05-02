package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static final String POPULATE_DB_FILENAME = "sql/populate_db.sql";
    private static PreparedStatement populateDb;
    public static void main(String[] args) {
        Connection connection = Database.getInstance().getConnection();
        try {
            String sql = Files.readString(Path.of(POPULATE_DB_FILENAME));
            populateDb = connection.prepareStatement(sql);
            populateDb.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
