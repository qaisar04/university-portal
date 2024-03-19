package kz.baltabayev.teacherservice.service.impl;

import kz.baltabayev.teacherservice.exception.TeacherNotFoundException;
import kz.baltabayev.teacherservice.mapper.TeacherMapper;
import kz.baltabayev.teacherservice.model.dto.TeacherRequest;
import kz.baltabayev.teacherservice.model.entity.Teacher;
import kz.baltabayev.teacherservice.repository.TeacherRepository;
import kz.baltabayev.teacherservice.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public Teacher save(TeacherRequest request) {
        Teacher teacher = teacherMapper.toEntity(request);
        teacher.setId(UUID.randomUUID().toString().split("-")[0]);
        return teacherRepository.save(teacher);
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
        return updatedTeacher;
    }
}
