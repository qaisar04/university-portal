package kz.baltabayev.teacherservice.model.payload;

public record FileUploadResponse(
        String fileName,
        String source,
        String url
) {
}