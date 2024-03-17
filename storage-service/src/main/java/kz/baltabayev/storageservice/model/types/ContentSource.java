package kz.baltabayev.storageservice.model.types;

import lombok.Getter;

/**
 * This enum represents the different sources of content that can be stored.
 * Each enum value is associated with a specific bucket name in the storage service.
 */
@Getter
public enum ContentSource {
    USER_PROFILE_IMAGE("user-profile-image-uni-portal"),
    PLACEHOLDER_IMAGE("placeholder-images-uni-portal-v1"),
    NEWS_CONTENT("news-content-image-uni-portal");

    /**
     * The name of the bucket associated with the content source.
     */
    private final String bucketName;

    /**
     * Constructs a new ContentSource with the specified bucket name.
     *
     * @param bucketName the name of the bucket associated with the content source.
     */
    ContentSource(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Returns the ContentSource that corresponds to the specified bucket name.
     *
     * @param bucketName the name of the bucket.
     * @return the ContentSource that corresponds to the specified bucket name.
     * @throws IllegalArgumentException if no ContentSource with the provided bucket name is found.
     */
    public static ContentSource fromBucketName(String bucketName) {
        for (ContentSource contentSource : ContentSource.values()) {
            if (contentSource.getBucketName().equals(bucketName)) {
                return contentSource;
            }
        }
        throw new IllegalArgumentException("No ContentSource with the provided bucketName found");
    }
}