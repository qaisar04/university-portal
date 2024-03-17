package kz.baltabayev.storageservice.repository;

import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * This interface represents a repository for managing S3File entities.
 * It extends JpaRepository, which provides methods for performing CRUD operations.
 */
public interface S3FileRepository extends JpaRepository<S3File, Long> {

    /**
     * Finds an S3File entity by its file name and source.
     *
     * @param fileName the name of the file.
     * @param source the source of the file content.
     * @return an Optional containing the found S3File entity, or an empty Optional if no entity was found.
     */
    Optional<S3File> findByFileNameAndSource(String fileName, ContentSource source);
}