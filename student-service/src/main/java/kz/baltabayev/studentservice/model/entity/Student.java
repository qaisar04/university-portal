package kz.baltabayev.studentservice.model.entity;

import jakarta.persistence.*;
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
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer course;
    private Double gpa;
    private Long facultyId;
    private Long departmentId;
    private String avatar;
}
// todo: генерация id по определенной логике
// example: 210107091 (21 - год поступления, 01 - факультет, 07 - специальность, 091 - код студента)
