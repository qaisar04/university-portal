package kz.baltabayev.studentservice.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(Long id) {
        super("Student with id %s not found".formatted(id));
    }
}
