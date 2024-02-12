package kz.baltabayev.subjectservice.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id) {
        super("Subject with id %s not found".formatted(id));
    }
}
