package kz.baltabayev.studentservice.model.entity;

import jakarta.persistence.*;
import kz.baltabayev.studentservice.model.enums.FacultyName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private FacultyName name;
    private String dean;
}
