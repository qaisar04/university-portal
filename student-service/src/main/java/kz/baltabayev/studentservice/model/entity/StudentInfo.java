package kz.baltabayev.studentservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@Table(name = "student_info")
@AllArgsConstructor
public class StudentInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer course;

    private Long facultyId;

    @Column(name = "birth_date")
    private LocalDate birthDate;
}
