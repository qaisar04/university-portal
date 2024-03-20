package kz.baltabayev.studentservice.service.impl;

import kz.baltabayev.studentservice.client.FacultyServiceClient;
import kz.baltabayev.studentservice.client.GradingServiceClient;
import kz.baltabayev.studentservice.client.StorageServiceClient;
import kz.baltabayev.studentservice.exception.InvalidArgumentException;
import kz.baltabayev.studentservice.exception.StudentNotFoundException;
import kz.baltabayev.studentservice.mapper.StudentMapper;
import kz.baltabayev.studentservice.model.dto.StudentRequest;
import kz.baltabayev.studentservice.model.dto.StudentResponse;
import kz.baltabayev.studentservice.model.entity.Student;
import kz.baltabayev.studentservice.model.payload.FileUploadResponse;
import kz.baltabayev.studentservice.repository.StudentRepository;
import kz.baltabayev.studentservice.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${aws.s3.bucket.profile-bucket}")
    private String STUDENT_PROFILE;

    @Value("${aws.s3.default.avatar}")
    private String DEFAULT_AVATAR;

    @Override
    public StudentResponse getInfo(Long id) {
        Student student = get(id);
        StudentResponse studentResponse = studentMapper.toResponse(student);
        studentResponse.setGpa(gradingServiceClient.getAverageScore(id).getBody());
        return studentResponse;
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile file) {
        Student student = get(id);
        FileUploadResponse[] fileUploadResponses = storageServiceClient.upload(STUDENT_PROFILE, id, file).getBody();
        assert fileUploadResponses != null;
        student.setAvatar(fileUploadResponses[0].url());
        studentRepository.saveAndFlush(student);
    }

    public void deleteAvatar(Long studentId) {
        Student student = get(studentId);
        String[] info = storageServiceClient.info(student.getAvatar()).getBody();
        assert info != null;
        storageServiceClient.delete(info[0], info[1]);
        student.setAvatar(DEFAULT_AVATAR);
        studentRepository.saveAndFlush(student);
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
    public Student save(StudentRequest studentRequest) {
        Student student = studentMapper.toStudent(studentRequest);
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
    public Student update(StudentRequest studentRequest, Long id) {
        Student existingStudent = get(id);
        Student updatedStudent = studentMapper.toStudent(studentRequest);
        updatedStudent.setId(existingStudent.getId());
        updatedStudent.setAvatar(existingStudent.getAvatar());
        return studentRepository.save(updatedStudent);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> getAllByFacultyId(Long facultyId) {
        return studentRepository.findAllByFacultyId(facultyId);
    }
}
