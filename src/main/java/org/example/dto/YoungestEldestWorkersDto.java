package org.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class YoungestEldestWorkersDto {
    private String Type;
    private String  name;
    private String birthday;
}
