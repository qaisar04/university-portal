package kz.baltabayev.storageservice.service;

import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;

import java.util.Optional;

/**
 * This interface represents a service for managing S3File entities.
 * It provides methods for saving, deleting, and retrieving S3File entities.
 */
public interface S3FileService {

    /**
     * Saves an S3File entity.
     *
     * @param s3File the S3File entity to save.
     * @return the saved S3File entity.
     */
    S3File save(S3File s3File);

    /**
     * Deletes an S3File entity by its id.
     *
     * @param id the id of the S3File entity to delete.
     */
    void delete(Long id);

    /**
     * Finds an S3File entity by its file name and source.
     *
     * @param fileName the name of the file.
     * @param source the source of the file content.
     * @return an Optional containing the found S3File entity, or an empty Optional if no entity was found.
     */
    Optional<S3File> getByFileNameAndSource(String fileName, ContentSource source);
}