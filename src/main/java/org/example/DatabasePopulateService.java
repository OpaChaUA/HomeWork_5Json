package org.example;
import org.example.insertDto.Client;
import org.example.insertDto.Project;
import org.example.insertDto.ProjectIdWorkerId;
import org.example.insertDto.Worker;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DatabasePopulateService {
    private static final String WORKERS_TXT = "sql2/workers.txt";
    private static final String CLIENT_TXT = "sql2/client.txt";
    private static final String PROJECTS_TXT = "sql2/projects.txt";
    private static final String PROJECT_WORKER_TXT = "sql2/project_worker.txt";
    private static PreparedStatement insert;
    Connection connection = Database.getInstance().getConnection();

    public String readFiles(String file) {
        String line = null;
        try {
            line = Files.readString(Path.of(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public List<Worker> AddWorkerInfo() {
        List<Worker> workerList = new ArrayList<>();
        String line = readFiles(WORKERS_TXT);
        Scanner sc = new Scanner(line);
        while (sc.hasNextLine()) {
            String res = sc.nextLine();
            Worker worker = new Worker();
            String[] query = res.split(", ");
            worker.setName(query[0]);
            worker.setBirthday(query[1]);
            worker.setLevel(query[2]);
            worker.setSalary(Integer.parseInt(query[3]));
            workerList.add(worker);
        }
        return workerList;
    }

    public void workerInsert() {
        List<Worker> workerList = AddWorkerInfo();
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO worker (name, birthday, level, salary) VALUES (?, ?, ?, ?)");
            for (Worker worker : workerList) {
                worker.getName();
                worker.getBirthday();
                worker.getLevel();
                worker.getSalary();
                insert.setString(1, worker.getName());
                insert.setString(2, worker.getBirthday());
                insert.setString(3, worker.getLevel());
                insert.setInt(4, worker.getSalary());
                insert.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Client> AddClientInfo() {
        List<Client> clientList = new ArrayList<>();
        String line = readFiles(CLIENT_TXT);
        Scanner sc = new Scanner(line);
        while (sc.hasNextLine()) {
            String res = sc.nextLine();
            Client client = new Client();
            String[] query = res.split(", ");
            client.setName(query[0]);
            clientList.add(client);
        }
        return clientList;
    }

    public void clientInsert() {
        List<Client> clientList = AddClientInfo();
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO client (name) VALUES (?)");
            for (Client client : clientList) {
                client.getName();
                insert.setString(1, client.getName());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<Project> AddProjectInfo() {
        List<Project> projectList = new ArrayList<>();
        String line = readFiles(PROJECTS_TXT);
        Scanner sc = new Scanner(line);
        while (sc.hasNextLine()) {
            String res = sc.nextLine();
            Project project = new Project();
            String[] query = res.split(", ");
            project.setClient_id(Integer.parseInt(query[0]));
            project.setName(query[1]);
            project.setStart_date(query[2]);
            project.setFinish_date(query[3]);
            projectList.add(project);
        }
        return projectList;
    }

    public void projectInsert() {
        List<Project> projectList = AddProjectInfo();
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO project (client_id, name, start_date, finish_date) VALUES (?, ?, ?, ?)");
            for (Project project:projectList) {
                project.getClient_id();
                project.getName();
                project.getStart_date();
                project.getFinish_date();
                insert.setInt(1, project.getClient_id());
                insert.setString(2, project.getName() );
                insert.setString(3, project.getStart_date());
                insert.setString(4, project.getFinish_date());
                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<ProjectIdWorkerId> AddProjectIdWorkerIdInfo() {
        List<ProjectIdWorkerId> projectIdWorkerIdList= new ArrayList<>();
        String line = readFiles(PROJECT_WORKER_TXT);
        Scanner sc = new Scanner(line);
        while (sc.hasNextLine()) {
            String res = sc.nextLine();
            ProjectIdWorkerId projectIdWorkerId = new ProjectIdWorkerId();
            String[] query = res.split(", ");
            projectIdWorkerId.setProject_id(Long.parseLong(query[0]));
            projectIdWorkerId.setWorker_id(Long.parseLong(query[1]));
            projectIdWorkerIdList.add(projectIdWorkerId);
        }
        return projectIdWorkerIdList;
    }

    public void projectIdWorkerIdInsert() {
        List<ProjectIdWorkerId> projectIdWorkerIdList = AddProjectIdWorkerIdInfo();
        try {
            insert = connection.prepareStatement(
                    "INSERT INTO project_worker (project_id, worker_id) VALUES (?,?)");
            for (ProjectIdWorkerId projectIdWorkerId: projectIdWorkerIdList) {
               projectIdWorkerId.getProject_id();
                 projectIdWorkerId.getWorker_id();

                insert.setLong(1,projectIdWorkerId.getProject_id());
                insert.setLong(2,projectIdWorkerId.getWorker_id());


                insert.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}





