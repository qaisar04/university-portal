package kz.baltabayev.subjectservice.service;

import kz.baltabayev.subjectservice.model.entity.Subject;

import java.util.Set;

public interface SubjectService {

    Subject getById(Long id);

    Subject save(Subject subject);

    Set<Subject> getByTeacherId(Set<Long> teacherId);

}
