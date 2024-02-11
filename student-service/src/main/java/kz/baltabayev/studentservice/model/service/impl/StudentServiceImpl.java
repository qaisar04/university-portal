package kz.baltabayev.studentservice.model.service.impl;

import kz.baltabayev.studentservice.client.GradingServiceClient;
import kz.baltabayev.studentservice.exception.StudentNotFoundException;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.enums.FacultyName;
import kz.baltabayev.studentservice.model.service.StudentService;
import kz.baltabayev.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GradingServiceClient gradingServiceClient;

    @Override
    public void delete(Long id) {
        gradingServiceClient.deleteByStudentId(id);
        studentRepository.deleteById(id);
    }

    @Override
    public Student get(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllByFaculty(FacultyName facultyName) {
        return studentRepository.findAllByFaculty(facultyName);
    }
}
