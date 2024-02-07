package kz.baltabayev.studentservice.repository;

import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.enums.FacultyName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFaculty(FacultyName facultyname);
}
