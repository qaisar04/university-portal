package kz.baltabayev.teacherservice.exception;

public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String id) {
        super("Teacher with id %s not found".formatted(id));
    }
}
