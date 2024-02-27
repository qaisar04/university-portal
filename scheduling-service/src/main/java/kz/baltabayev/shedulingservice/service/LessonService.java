package kz.baltabayev.shedulingservice.service;

import kz.baltabayev.shedulingservice.model.entity.Lesson;

public interface LessonService {

    Lesson getById(Long id);

    Lesson save(Lesson lesson);

    void delete(Long id);
}
