package kz.baltabayev.storageservice.model.types;

import lombok.Getter;

@Getter
public enum ContentSource {
    USER_PROFILE_IMAGE("user-profile-image-uni-portal"),
    NEWS_CONTENT("news-content-image-uni-portal");

    private final String bucketName;

    ContentSource(String bucketName) {
        this.bucketName = bucketName;
    }
}