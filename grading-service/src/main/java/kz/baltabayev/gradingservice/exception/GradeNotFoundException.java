package kz.baltabayev.gradingservice.exception;

/**
 * Exception thrown when a grade is not found.
 * This exception is used to indicate that a grade with a specific ID does not exist.
 */
public class GradeNotFoundException extends RuntimeException {

    /**
     * Constructs a new GradeNotFoundException with a detail message.
     *
     * @param id the ID of the grade that was not found
     */
    public GradeNotFoundException(Long id) {
        super("Grade with id %s not found".formatted(id));
    }
}
