package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = Database.getInstance().getConnection();

        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.workerInsert();
        databasePopulateService.clientInsert();
        databasePopulateService.projectInsert();
        databasePopulateService.projectIdWorkerIdInsert();




       DatabaseQueryService databaseQueryService = new DatabaseQueryService();
        System.out.println(databaseQueryService.findMaxSalaryWorker());
       System.out.println(databaseQueryService.findMaxProjectClient());
        System.out.println(databaseQueryService.findLongestProject());
        System.out.println(databaseQueryService.findYoungestEldestWorkers());
        System.out.println(databaseQueryService.printProjectPrices());

    }
}
