package kz.baltabayev.studentservice.model.payload;

public record FileUploadResponse(
        String fileName,
        String source,
        String url
) {
}