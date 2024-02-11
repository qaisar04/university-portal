package kz.baltabayev.studentservice.model.dto;

public record GradeResponse(
        Double score,
        Long studentId,
        Long subjectId
) {
}
