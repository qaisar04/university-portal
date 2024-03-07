package kz.baltabayev.studentservice.model.dto;

import jakarta.validation.constraints.*;
import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentRequest {
    @NotBlank(message = "firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "lastname cannot be blank")
    private String lastname;

    @Past(message = "birthdate should be in the past")
    private LocalDate birthdate;

    @Email(message = "email should be valid")
    private String email;

    @NotNull(message = "gender cannot be null")
    private Gender gender;

    @Min(value = 1, message = "course should be greater than or equal to 1")
    @Max(value = 5, message = "course should be less than or equal to 5")
    private Integer course;

    @NotNull(message = "facultyId cannot be null")
    private Long facultyId;

    @NotNull(message = "departmentId cannot be null")
    private Long departmentId;
}