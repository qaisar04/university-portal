package kz.baltabayev.teacherservice.model.dto;

import kz.baltabayev.teacherservice.model.enums.Gender;

import java.time.LocalDate;

public record TeacherRequest(
        String firstname,
        String lastname,
        LocalDate birthDate,
        String email,
        Gender gender
) {
}
