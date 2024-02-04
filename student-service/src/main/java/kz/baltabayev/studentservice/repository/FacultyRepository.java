package kz.baltabayev.studentservice.repository;

import kz.baltabayev.studentservice.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
