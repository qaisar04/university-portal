package kz.baltabayev.gradingservice.repository;

import kz.baltabayev.gradingservice.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
