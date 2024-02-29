package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;

import java.util.Optional;

public interface S3FileService {

    S3File save(S3File s3File);

    void delete(Long id);

    Optional<S3File> getByFileNameAndSource(String fileName, ContentSource source);
}
