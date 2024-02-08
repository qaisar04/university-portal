package kz.baltabayev.studentservice.model.service;

import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.enums.FacultyName;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void delete(Long id);

    Optional<Student> get(Long id);

    Student save(Student student);

    Student update(Student student);

    List<Student> getAll();

    List<Student> getAllByFaculty(FacultyName facultyName);
}
