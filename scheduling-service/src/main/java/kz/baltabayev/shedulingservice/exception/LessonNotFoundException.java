package kz.baltabayev.shedulingservice.exception;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(Long id) {
        super("Lesson with id %s not found".formatted(id));
    }
}
