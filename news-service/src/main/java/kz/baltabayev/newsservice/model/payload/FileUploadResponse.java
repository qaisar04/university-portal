package kz.baltabayev.newsservice.model.payload;

public record FileUploadResponse(
        String id,
        String source,
        String url
) {
}
