package kz.baltabayev.storageservice.model.dto;

/**
 * This class represents a response from a file upload operation.
 * It is a record, which is a special kind of class in Java that is used to model immutable data.
 * Each instance of this class contains the name of the file, the source where it was uploaded, and the URL where it can be accessed.
 */
public record FileUploadResponse(
        String fileName,
        String source,
        String url
) {
}