package kz.baltabayev.studentservice.model.entity;

import jakarta.persistence.*;
import kz.baltabayev.studentservice.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Gender gender;
    private Integer course;
    @ManyToOne
    private Faculty faculty;
}
