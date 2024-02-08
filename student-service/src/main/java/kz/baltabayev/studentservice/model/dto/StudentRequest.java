package kz.baltabayev.studentservice.model.dto;

import kz.baltabayev.studentservice.model.enums.FacultyName;
import kz.baltabayev.studentservice.model.enums.Gender;

import java.time.LocalDate;

public record StudentRequest(
        String name,
        String lasname,
        LocalDate birthdate,
        String email,
        Gender gender,
        Integer course,
        FacultyName faculty
) {
}
