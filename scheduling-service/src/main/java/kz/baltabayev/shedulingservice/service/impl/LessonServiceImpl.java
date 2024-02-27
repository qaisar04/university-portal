package kz.baltabayev.shedulingservice.service.impl;

import kz.baltabayev.shedulingservice.exception.LessonNotFoundException;
import kz.baltabayev.shedulingservice.model.entity.Lesson;
import kz.baltabayev.shedulingservice.repository.LessonRepository;
import kz.baltabayev.shedulingservice.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public Lesson getById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
    }

    @Override
    public Lesson save(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void delete(Long id) {
        lessonRepository.deleteById(id);
    }
}
