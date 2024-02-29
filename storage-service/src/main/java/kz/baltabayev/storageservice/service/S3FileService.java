package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.entity.S3File;

public interface S3FileService {

    S3File save(S3File s3File);

    void delete(Long id);
}
