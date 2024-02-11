package kz.baltabayev.gradingservice.model.dto;

public record GradeDto(Double score,
                       Long studentId,
                       Long subjectId
) {
}
