package kz.baltabayev.subjectservice.service;

import kz.baltabayev.subjectservice.model.dto.SubjectDto;
import kz.baltabayev.subjectservice.model.entity.Subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {

    Subject getById(Long id);

    List<Subject> getAll();

    Subject save(Subject subject);

    Subject update(Long id, SubjectDto dto);

    void delete(Long id);

    Set<Subject> getByTeacherId(Set<Long> teacherId);
}
