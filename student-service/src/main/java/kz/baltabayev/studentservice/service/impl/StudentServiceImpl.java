package kz.baltabayev.studentservice.service.impl;

import kz.baltabayev.studentservice.client.GradingServiceClient;
import kz.baltabayev.studentservice.client.StorageServiceClient;
import kz.baltabayev.studentservice.exception.StudentNotFoundException;
import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.dto.StudentResponse;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.service.StudentService;
import kz.baltabayev.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GradingServiceClient gradingServiceClient;
    private final StorageServiceClient storageServiceClient;
    private final StudentMapper studentMapper;
    private final static String STUDENT_PROFILE = "USER_PROFILE_IMAGE";

    @Override
    public StudentResponse getInfo(Long id) {
        Student student = get(id);
        StudentResponse studentResponse = studentMapper.toDto(student);
        studentResponse.setGpa(gradingServiceClient.getAverageScore(id).getBody());
        return studentResponse;
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile file) {
        Student student = get(id);
        ResponseEntity<String> uploadImage = storageServiceClient.upload(STUDENT_PROFILE, id, file);
        student.setAvatar(uploadImage.getBody());
        update(student);
    }

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

    public List<Student> getAllByFacultyId(Long facultyId) {
        return studentRepository.findAllByFacultyId(facultyId);
    }
}
