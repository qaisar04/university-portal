package kz.baltabayev.gradingservice.repository;

import kz.baltabayev.gradingservice.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);

    void deleteByStudentId(Long studentId);

    List<Grade> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
}
