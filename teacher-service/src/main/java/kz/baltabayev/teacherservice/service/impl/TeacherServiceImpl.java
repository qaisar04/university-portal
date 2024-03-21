package kz.baltabayev.teacherservice.service.impl;

import kz.baltabayev.teacherservice.client.StorageServiceClient;
import kz.baltabayev.teacherservice.exception.TeacherNotFoundException;
import kz.baltabayev.teacherservice.mapper.TeacherMapper;
import kz.baltabayev.teacherservice.model.dto.TeacherRequest;
import kz.baltabayev.teacherservice.model.entity.Teacher;
import kz.baltabayev.teacherservice.model.payload.FileUploadResponse;
import kz.baltabayev.teacherservice.repository.TeacherRepository;
import kz.baltabayev.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final StorageServiceClient storageServiceClient;
    private final TeacherMapper teacherMapper;

    @Value("${aws.s3.bucket.profile-bucket}")
    private String TEACHER_PROFILE;

    @Override
    public Teacher save(TeacherRequest request) {
        Teacher teacher = teacherMapper.toEntity(request);
        teacher.setId(UUID.randomUUID().toString().split("-")[0]);
        return teacherRepository.save(teacher);
    }

    public void uploadAvatar(String id, MultipartFile file) {
        Teacher teacher = get(id);
        FileUploadResponse[] responses = storageServiceClient.upload(TEACHER_PROFILE, id, file).getBody();
        assert responses != null;
        teacher.setAvatar(responses[0].url());
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher get(String id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @Override
    public void delete(String id) {
        Teacher teacher = get(id);
        teacherRepository.delete(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher update(TeacherRequest request, String id) {
        Teacher existingTeacher = get(id);
        Teacher updatedTeacher = teacherMapper.toEntity(request);
        updatedTeacher.setId(existingTeacher.getId());
        updatedTeacher.setAvatar(existingTeacher.getAvatar());
        return teacherRepository.save(updatedTeacher);
    }
}
