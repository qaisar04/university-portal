package kz.baltabayev.subjectservice.repository;

import kz.baltabayev.subjectservice.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Set<Subject> findAllByTeacherIds(Set<Long> teacherId);
}
