package kz.baltabayev.studentservice.model.entity;

import jakarta.persistence.*;
import kz.baltabayev.studentservice.model.enums.FacultyName;
import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String mail;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer course;
    @Enumerated(EnumType.STRING)
    private FacultyName faculty;
}
