package kz.baltabayev.studentservice.model.dto;

import kz.baltabayev.studentservice.model.enums.FacultyName;

import java.time.LocalDate;

public class StudentRequest {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String mail;
    private Integer course;
    private FacultyName faculty;
}
