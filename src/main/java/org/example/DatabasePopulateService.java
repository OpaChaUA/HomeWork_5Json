package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.insertDto.Client;
import org.example.insertDto.Project;
import org.example.insertDto.ProjectIdWorkerId;
import org.example.insertDto.Worker;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DatabasePopulateService {
    private static final String WORKERS_JSON = "sql2/worker.json";
    private static final String CLIENT_JSON = "sql2/client.json";
    private static final String PROJECTS_JSON = "sql2/project.json";
    private static final String PROJECT_WORKER_JSON = "sql2/projectWorker.json";
    private static PreparedStatement insert;
    Connection connection = Database.getInstance().getConnection();

    public <T> List<T> readFilesFromJson(Class<T> infoClass, String file) {
        List<T> infoList = new ArrayList<>();
        try (Reader fileReader = new FileReader(file)) {
            Type type = TypeToken.getParameterized(List.class, infoClass).getType();
            infoList = new Gson().fromJson(fileReader, type);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return infoList;
    }

    public void insertWorker() {
        List<Worker> workerList = readFilesFromJson(Worker.class, WORKERS_JSON);
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)");
            for (Worker worker : workerList) {
                insert.setString(1, worker.getName());
                insert.setString(2, worker.getBirthday());
                insert.setString(3, worker.getLevel());
                insert.setInt(4, worker.getSalary());
                insert.addBatch();
            }
            insert.executeBatch();
            insert.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertClient() {
        List<Client> clientList = readFilesFromJson(Client.class, CLIENT_JSON);
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO client (name) VALUES (?)");
            for (Client client : clientList) {
                insert.setString(1, client.getName());
                insert.addBatch();
            }
            insert.executeBatch();
            insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProject() {
        List<Project> projectList = readFilesFromJson(Project.class, PROJECTS_JSON);
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO project (name, client_id, start_date, finish_date) VALUES (?, ?, ?, ?)")){
            for (Project project : projectList) {
                preparedStatement.setString(1, project.getName());
                preparedStatement.setLong(2, project.getClientId());
                preparedStatement.setString(3, project.getStartDate());
                preparedStatement.setString(4, project.getFinishDate());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertProjectWorker() {
        List<ProjectIdWorkerId> projectIdWorkerIdList = readFilesFromJson(ProjectIdWorkerId.class, PROJECT_WORKER_JSON);
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO project_worker (project_id , worker_id) VALUES (?, ?)");
            for (ProjectIdWorkerId projectIdWorkerId : projectIdWorkerIdList) {
                insert.setLong(1, projectIdWorkerId.getProjectId());
                insert.setLong(2, projectIdWorkerId.getWorkerId());
                insert.addBatch();
            }
            insert.executeBatch();
            insert.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}





