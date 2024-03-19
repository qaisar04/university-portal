package kz.baltabayev.teacherservice.service;

import kz.baltabayev.teacherservice.model.dto.TeacherRequest;
import kz.baltabayev.teacherservice.model.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher save(TeacherRequest request);

    Teacher get(String id);

    void delete(String id);

    List<Teacher> getAll();

    Teacher update(TeacherRequest request, String id);
}
