package kz.baltabayev.teacherservice.controller.advice;

import kz.baltabayev.teacherservice.exception.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a global exception handler for the application.
 * It catches exceptions that are not caught at the controller level.
 * The @RestControllerAdvice annotation makes it applicable to all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles TeacherNotFoundException.
     * When a TeacherNotFoundException is thrown, this method will be invoked to handle it.
     * It returns a ProblemDetail object with the status set to NOT_FOUND and detail set to the exception's message.
     *
     * @param exception the TeacherNotFoundException that is caught.
     * @return a ProblemDetail object with the status and detail set.
     */
    @ExceptionHandler(TeacherNotFoundException.class)
    ProblemDetail handlerTeacherNotFoundException(TeacherNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}