package kz.baltabayev.studentservice.service.impl;

import kz.baltabayev.studentservice.client.FacultyServiceClient;
import kz.baltabayev.studentservice.client.GradingServiceClient;
import kz.baltabayev.studentservice.client.StorageServiceClient;
import kz.baltabayev.studentservice.exception.InvalidArgumentException;
import kz.baltabayev.studentservice.exception.StudentNotFoundException;
import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.StudentResponse;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.repository.StudentRepository;
import kz.baltabayev.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GradingServiceClient gradingServiceClient;
    private final StorageServiceClient storageServiceClient;
    private final FacultyServiceClient facultyServiceClient;
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
        if (student == null || student.getFacultyId() == null || student.getDepartmentId() == null) {
            throw new InvalidArgumentException("Student, facultyId or departmentId cannot be null");
        }

        boolean isExist = facultyServiceClient.isExist(student.getFacultyId(), student.getDepartmentId());

        if (isExist) {
            log.info("Saving student with id: {}", student.getId());
            return studentRepository.save(student);
        } else {
            log.error("Faculty with id: {} or department with id: {} does not exist", student.getFacultyId(), student.getDepartmentId());
            throw new InvalidArgumentException("Faculty or department does not exist");
        }
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
