package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection connection = Database.getInstance().getConnection();

        DatabaseQueryService databaseQueryService = new DatabaseQueryService();
        System.out.println(databaseQueryService.findMaxSalaryWorker());
        System.out.println(databaseQueryService.findMaxProjectClient());
        System.out.println(databaseQueryService.findLongestProject());
        System.out.println(databaseQueryService.findYoungestEldestWorkers());
        System.out.println(databaseQueryService.printProjectPrices());
    }
}
