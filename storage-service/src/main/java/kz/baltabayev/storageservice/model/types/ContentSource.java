package kz.baltabayev.storageservice.model.types;

import lombok.Getter;

@Getter
public enum ContentSource {
    USER_PROFILE_IMAGE("user-profile-image-uni-portal"),
    PLACEHOLDER_IMAGE("placeholder-images-uni-portal-v1"),
    NEWS_CONTENT("news-content-image-uni-portal");

    private final String bucketName;

    ContentSource(String bucketName) {
        this.bucketName = bucketName;
    }

    public static ContentSource fromBucketName(String bucketName) {
        for (ContentSource contentSource : ContentSource.values()) {
            if (contentSource.getBucketName().equals(bucketName)) {
                return contentSource;
            }
        }
        throw new IllegalArgumentException("No ContentSource with the provided bucketName found");
    }
}