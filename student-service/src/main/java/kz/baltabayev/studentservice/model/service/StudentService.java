package kz.baltabayev.studentservice.model.service;

import kz.baltabayev.studentservice.model.entity.Student;

import java.util.List;

public interface StudentService {

    void delete(Long id);

    Student get(Long id);

    Student save(Student student);

    Student update(Student student);

    List<Student> getAll();

    List<Student> getAllByFacultyId(Long facultyId);
}
