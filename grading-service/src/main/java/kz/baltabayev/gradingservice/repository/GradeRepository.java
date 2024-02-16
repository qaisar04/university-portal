package kz.baltabayev.gradingservice.repository;

import kz.baltabayev.gradingservice.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for the Grade entity.
 * This interface allows to perform CRUD operations on the Grade entity in the database.
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    /**
     * Finds all grades for a specific student.
     *
     * @param studentId the ID of the student
     * @return a list of grades for the student
     */
    List<Grade> findByStudentId(Long studentId);

    /**
     * Deletes all grades for a specific student.
     *
     * @param studentId the ID of the student
     */
    void deleteByStudentId(Long studentId);

    /**
     * Finds all grades for a specific student and subject.
     *
     * @param studentId the ID of the student
     * @param subjectId the ID of the subject
     * @return a list of grades for the student and subject
     */
    List<Grade> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
}