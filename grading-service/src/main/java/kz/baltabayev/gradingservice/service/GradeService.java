package kz.baltabayev.gradingservice.service;

import kz.baltabayev.gradingservice.model.dto.GradeDto;
import kz.baltabayev.gradingservice.model.dto.GradeResponse;
import kz.baltabayev.gradingservice.model.entity.Grade;

import java.util.List;
import java.util.Map;

/**
 * Service interface for the Grade entity.
 * This interface defines the operations that can be performed on grades.
 */
public interface GradeService {

    /**
     * Saves a grade in the database.
     *
     * @param grade the grade to save
     * @return the saved grade as a DTO
     */
    GradeDto save(Grade grade);

    /**
     * Retrieves all grades for a specific student, grouped by subject.
     *
     * @param studentId the ID of the student
     * @return a map where the key is the subject ID and the value is a list of grades for that subject
     */
    Map<Long, List<GradeResponse>> getByStudentId(Long studentId);

    /**
     * Deletes a grade by its ID.
     *
     * @param id the ID of the grade
     */
    void deleteById(Long id);

    /**
     * Deletes all grades for a specific student.
     *
     * @param studentId the ID of the student
     */
    void deleteByStudentId(Long studentId);

    /**
     * Updates a grade in the database.
     *
     * @param grade the grade to update
     * @return the updated grade
     */
    Grade update(Grade grade);

    /**
     * Calculates the average score for a specific student.
     *
     * @param studentId the ID of the student
     * @return the average score, or 0.0 if the student has no grades
     */
    Double getAverageScoreByStudentId(Long studentId);

    /**
     * Retrieves all grades for a specific student and subject.
     *
     * @param studentId the ID of the student
     * @param subjectId the ID of the subject
     * @return a list of grades for the student and subject
     */
    List<Grade> getByStudentIdAndSubjectId(Long studentId, Long subjectId);
}