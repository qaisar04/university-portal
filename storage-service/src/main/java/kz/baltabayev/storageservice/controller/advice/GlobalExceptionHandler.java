package kz.baltabayev.storageservice.controller.advice;

import kz.baltabayev.storageservice.exception.FileDownloadException;
import kz.baltabayev.storageservice.exception.InvalidFileTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFileTypeException.class)
    ProblemDetail handlerInvalidFileTypeException(InvalidFileTypeException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(FileDownloadException.class)
    ProblemDetail handlerFileDownloadException(FileDownloadException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
