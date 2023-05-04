package org.example.insertDto;

import lombok.Data;

@Data
public class Project {
    private int client_id;
    private String name;
    private String start_date;
    private String finish_date;

}
