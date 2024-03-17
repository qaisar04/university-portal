package kz.baltabayev.storageservice.controller.advice;

import kz.baltabayev.storageservice.exception.FileDownloadException;
import kz.baltabayev.storageservice.exception.InvalidFileTypeException;
import kz.baltabayev.storageservice.exception.InvalidUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is a global exception handler for the application.
 * It catches exceptions thrown throughout the application and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles InvalidFileTypeException.
     * This exception is thrown when the file type is not supported.
     *
     * @param exception the exception thrown
     * @return a ProblemDetail object containing the HTTP status and error message
     */
    @ExceptionHandler(InvalidFileTypeException.class)
    ProblemDetail handlerInvalidFileTypeException(InvalidFileTypeException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * Handles FileDownloadException.
     * This exception is thrown when there is an error downloading a file.
     *
     * @param exception the exception thrown
     * @return a ProblemDetail object containing the HTTP status and error message
     */
    @ExceptionHandler(FileDownloadException.class)
    ProblemDetail handlerFileDownloadException(FileDownloadException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    /**
     * Handles InvalidUrlException.
     * This exception is thrown when the provided URL is invalid.
     *
     * @param exception the exception thrown
     * @return a ProblemDetail object containing the HTTP status and error message
     */
    @ExceptionHandler(InvalidUrlException.class)
    ProblemDetail handlerInvalidUrlException(InvalidUrlException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}