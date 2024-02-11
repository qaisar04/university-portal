package kz.baltabayev.gradingservice.exception;

public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long id) {
        super("Grade with id %s not found".formatted(id));
    }
}
