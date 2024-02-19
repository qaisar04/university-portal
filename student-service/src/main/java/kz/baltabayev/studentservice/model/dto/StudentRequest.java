package kz.baltabayev.studentservice.model.dto;

import jakarta.validation.constraints.Max;
import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
    private String name;
    private String lasname;
    private LocalDate birthdate;
    private String email;
    private Gender gender;
    private Integer course;
    @Max(4)
    private Double gpa;
    private Long facultyId;
}
