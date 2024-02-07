package kz.baltabayev.studentservice.model.service;

import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.enums.FacultyName;
import kz.baltabayev.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> getStuden(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByFaculty(FacultyName facultyName) {
        return studentRepository.findAllByFacultyName(facultyName);
    }
}
