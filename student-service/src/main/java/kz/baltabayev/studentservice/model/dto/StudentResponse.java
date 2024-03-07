package kz.baltabayev.studentservice.model.dto;

import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class StudentResponse {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private Gender gender;
    private Integer course;
    private String avatar;
    private Double gpa;
    private Long facultyId;
    private Long departmentId;
}