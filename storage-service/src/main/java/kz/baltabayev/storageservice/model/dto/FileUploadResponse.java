package kz.baltabayev.storageservice.model.dto;

public record FileUploadResponse(
        String fileName,
        String source,
        String url
) {
}
