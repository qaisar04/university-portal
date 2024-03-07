package kz.baltabayev.studentservice.model.payload;

public record Department(
        Long id,
        String name,
        String head,
        Long facultyId
) {
}
