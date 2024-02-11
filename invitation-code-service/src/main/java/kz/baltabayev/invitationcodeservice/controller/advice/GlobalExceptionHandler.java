package kz.baltabayev.invitationcodeservice.controller.advice;

import kz.baltabayev.invitationcodeservice.exception.InvalidCodeException;
import kz.baltabayev.invitationcodeservice.exception.InvalidRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRoleException.class)
    ProblemDetail hadnlerInvalidRoleException(Exception exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidCodeException.class)
    ProblemDetail hadnlerInvalidCodeException(Exception exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
