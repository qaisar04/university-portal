package kz.baltabayev.gradingservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a grade entity in the database.
 * Each grade is associated with a student and a subject.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grades")
public class Grade {

    /**
     * The unique identifier of the grade.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The score of the grade.
     */
    private Double score;

    /**
     * The identifier of the student who received the grade.
     */
    private Long studentId;

    /**
     * The identifier of the subject for which the grade was given.
     */
    private Long subjectId;
}
