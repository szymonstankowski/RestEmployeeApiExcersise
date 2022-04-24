package pl.szymonstankowski.RestApiExcersise.model;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private boolean active;
    private LocalDate created;
}
