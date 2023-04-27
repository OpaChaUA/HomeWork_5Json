package org.example;

import org.example.dto.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final String FIND_MAX_SALARY_WORKER_FILENAME = "sql/find_max_salary_worker.sql";
    private static final String FIND_MAX_PROJECTS_CLIENT_FILENAME = "sql/find_max_projects_client.sql";
    private static final String FIND_LONGEST_PROJECT_FILENAME = "sql/find_longest_project.sql";
    private static final String FIND_YOUNGEST_ELDEST_WORKERS_FILENAME = "sql/find_youngest_eldest_workers.sql";
    private static final String PRINT_PROJECT_PRICES_FILENAME = "sql/print_project_prices.sql";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_PROJECT_COUNT = "project_count";
    private static final String COLUMN_MONTH_COUNT = "month_count";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_PRICE = "price";
    private final Connection connection = Database.getInstance().getConnection();

    public List<MaxSalaryDto> findMaxSalaryWorker() {
        List<MaxSalaryDto> maxSalaryDtoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_MAX_SALARY_WORKER_FILENAME));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                MaxSalaryDto maxSalaryDto = new MaxSalaryDto();
                maxSalaryDto.setName(rs.getString(COLUMN_NAME));
                maxSalaryDto.setSalary(rs.getInt(COLUMN_SALARY));
                maxSalaryDtoList.add(maxSalaryDto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxSalaryDtoList;
    }

    public List<MaxProjectClientDto> findMaxProjectClient () {
        List<MaxProjectClientDto> maxProjectClientDtoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT_FILENAME));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                MaxProjectClientDto maxProjectClientDto = new MaxProjectClientDto();
                maxProjectClientDto.setName(rs.getString(COLUMN_NAME));
                maxProjectClientDto.setCountProject(rs.getInt(COLUMN_PROJECT_COUNT));
                maxProjectClientDtoList.add(maxProjectClientDto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return maxProjectClientDtoList;
    }

    public List<LongestProjectDto> findLongestProject() {
        List<LongestProjectDto> longestProjectDtoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_LONGEST_PROJECT_FILENAME));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                LongestProjectDto longestProjectDto = new LongestProjectDto();
                longestProjectDto.setName(rs.getString(COLUMN_NAME));
                longestProjectDto.setMonth_count(rs.getInt(COLUMN_MONTH_COUNT));
                longestProjectDtoList.add(longestProjectDto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return longestProjectDtoList;
    }

    public List<YoungestEldestWorkersDto> findYoungestEldestWorkers() {
        List<YoungestEldestWorkersDto> youngestEldestWorkersDtoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKERS_FILENAME));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                YoungestEldestWorkersDto youngestEldestWorkersDto = new YoungestEldestWorkersDto();
                youngestEldestWorkersDto.setType(rs.getString(COLUMN_TYPE));
                youngestEldestWorkersDto.setName(rs.getString(COLUMN_NAME));
                youngestEldestWorkersDto.setBirthday(rs.getString(COLUMN_BIRTHDAY));
                youngestEldestWorkersDtoList.add(youngestEldestWorkersDto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return youngestEldestWorkersDtoList;
    }

    public List<PrintProjectPricesDto> printProjectPrices() {
        List<PrintProjectPricesDto> printProjectPricesDtoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = Files.readString(Path.of(PRINT_PROJECT_PRICES_FILENAME));
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                PrintProjectPricesDto printProjectPricesDto = new PrintProjectPricesDto();
                printProjectPricesDto.setName(rs.getString(COLUMN_NAME));
                printProjectPricesDto.setPriceProject(rs.getInt(COLUMN_PRICE));
                printProjectPricesDtoList.add(printProjectPricesDto);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return printProjectPricesDtoList;
    }


}
