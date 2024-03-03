package kz.baltabayev.studentservice.model.dto;

import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
public class StudentRequest {
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String email;
    private Gender gender;
    private Integer course;
    private Long facultyId;
}
