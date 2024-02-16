package kz.baltabayev.gradingservice.controller.advice;

import kz.baltabayev.gradingservice.exception.GradeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * This class handles all exceptions that are not caught at the controller level.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles GradeNotFoundExceptions.
     *
     * @param exception the exception to handle
     * @return a ProblemDetail object containing the HTTP status and the exception message
     */
    @ExceptionHandler(GradeNotFoundException.class)
    ProblemDetail handleGradeNotFoundException(GradeNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}