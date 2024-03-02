package kz.baltabayev.studentservice.model.service.impl;

import kz.baltabayev.studentservice.client.GradingServiceClient;
import kz.baltabayev.studentservice.client.StorageServiceClient;
import kz.baltabayev.studentservice.exception.StudentNotFoundException;
import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.GradeResponse;
import kz.baltabayev.studentservice.model.dto.StudentInfoResponse;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.service.StudentService;
import kz.baltabayev.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GradingServiceClient gradingServiceClient;
    private final StorageServiceClient storageServiceClient;
    private final StudentMapper studentMapper;

    @Override
    public StudentInfoResponse getInfo(Long id) {
        Student student = get(id);
        StudentRequest studentRequest = studentMapper.toDto(student);
//        Map<Long, List<GradeResponse>> grades = gradingServiceClient.getByStudentId(id).getBody();
        Map<Long, List<GradeResponse>> grades = new HashMap<>();
//        studentRequest.setGpa(gradingServiceClient.getAverageScore(id).getBody());
        return new StudentInfoResponse(studentRequest, grades);
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile file) {
        Student student = get(id);
        ResponseEntity<String> uploadImage = storageServiceClient.uploadImage("USER_PROFILE_IMAGE", id, file);
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
