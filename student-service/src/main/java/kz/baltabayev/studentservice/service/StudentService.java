package kz.baltabayev.studentservice.service;

import kz.baltabayev.studentservice.model.dto.StudentResponse;
import kz.baltabayev.studentservice.model.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    void delete(Long id);

    Student get(Long id);

    Student save(Student student);

    Student update(Student student);

    List<Student> getAll();

    void uploadAvatar(Long id, MultipartFile file);

    StudentResponse getInfo(Long id);
}