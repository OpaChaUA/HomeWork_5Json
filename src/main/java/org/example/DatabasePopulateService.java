package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    private static final String WORKERS_TXT = "sql2/workers.txt";
    private static final String CLIENT_TXT = "sql2/client.txt";
    private static final String PROJECTS_TXT ="sql2/projects.txt";
    private static final String PROJECT_WORKER_TXT = "sql2/project_worker.txt";
    private static PreparedStatement insert;

    public void insertWorkers() {

        Connection connection = Database.getInstance().getConnection();
        try {
            String input = Files.readString(Path.of(WORKERS_TXT));
            String[] rows = input.split("\n");

            String[] name = new String[rows.length];
            String[] birthdate = new String[rows.length];
            String[] position = new String[rows.length];
            int[] salary = new int[rows.length];

            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(", ");
                name[i] = cols[0];
                birthdate[i] = cols[1];
                position[i] = cols[2];
                salary[i] = Integer.parseInt(cols[3]);
            }
            insert = connection.prepareStatement("INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)");
            for (int i = 0; i < name.length; i++) {
                String names = name[i];
                String birthdates = birthdate[i];
                String positions = position[i];
                int salarys = salary[i];
                insert.setString(1, names);
                insert.setString(2, birthdates);
                insert.setString(3, positions);
                insert.setInt(4, salarys);
                insert.addBatch();
            }
            insert.executeBatch();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void insertClients() {
        Connection connection = Database.getInstance().getConnection();
        try {
            String input = Files.readString(Path.of(CLIENT_TXT));
            String[] rows = input.split("\n");

            String[] name = new String[rows.length];

            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(", ");
                name[i] = cols[0];
            }
            insert = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
            for (int i = 0; i < name.length; i++) {
                String names = name[i];
                insert.setString(1, names);
                insert.addBatch();
            }
            insert.executeBatch();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public void insertProjects(){
        Connection connection = Database.getInstance().getConnection();
        try {
            String input = Files.readString(Path.of(PROJECTS_TXT));
            String[] rows = input.split("\n");

            int[] client_id = new int[rows.length];
            String[] name = new String[rows.length];
            String[] startDate = new String[rows.length];
            String[] finisDate = new String[rows.length];

            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(",");
                client_id[i] = Integer.parseInt(cols[0]);
                name[i] = cols[1];
                startDate[i] = cols[2];
                finisDate[i] = (cols[3]);
            }
            insert = connection.prepareStatement("INSERT INTO project (client_id, name, start_date, finish_date) VALUES (?, ?, ?, ?)");
            for (int i = 0; i < name.length; i++) {
                int id = client_id[i];
                String names = name[i];
                String startDates = startDate[i];
                String finishDates= finisDate[i];
                insert.setInt(1, id);
                insert.setString(2, names);
                insert.setString(3, startDates);
                insert.setString(4, finishDates);
                insert.addBatch();
            }
            insert.executeBatch();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public void insertProjectWorkers(){
        Connection connection = Database.getInstance().getConnection();
        try {
            String input = Files.readString(Path.of(PROJECT_WORKER_TXT));
            String[] rows = input.split("\n");

            long[] project_id = new long[rows.length];
            long[] worker_id = new long[rows.length];


            for (int i = 0; i < rows.length; i++) {
                String[] cols = rows[i].split(",");
                project_id[i] = Long.parseLong(cols[0]);
                worker_id[i] =Long.parseLong(cols[1]);

            }
            insert = connection.prepareStatement("INSERT INTO project_worker (project_id, worker_id) VALUES (?,?)");
            for (int i = 0; i < rows.length; i++) {
                long pID = (project_id[i]);
                long wID = (worker_id[i]);
                insert.setLong(1, pID);
                insert.setLong(2, wID);
                insert.addBatch();
            }
            insert.executeBatch();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    }


