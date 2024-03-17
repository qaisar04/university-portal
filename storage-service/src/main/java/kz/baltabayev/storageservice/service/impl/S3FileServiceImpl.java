package kz.baltabayev.storageservice.service.impl;

import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;
import kz.baltabayev.storageservice.repository.S3FileRepository;
import kz.baltabayev.storageservice.service.S3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class implements the S3FileService interface.
 * It is annotated with @Service, indicating that it is a Spring service.
 * It uses Lombok's @RequiredArgsConstructor to automatically generate a constructor with required fields.
 */
@Service
@RequiredArgsConstructor
public class S3FileServiceImpl implements S3FileService {

    /**
     * The repository for managing S3File entities.
     */
    private final S3FileRepository s3FileRepository;

    /**
     * Saves an S3File entity.
     *
     * @param s3File the S3File entity to save.
     * @return the saved S3File entity.
     */
    @Override
    public S3File save(S3File s3File) {
        return s3FileRepository.save(s3File);
    }

    /**
     * Deletes an S3File entity by its id.
     *
     * @param id the id of the S3File entity to delete.
     */
    @Override
    public void delete(Long id) {
        s3FileRepository.deleteById(id);
    }

    /**
     * Finds an S3File entity by its file name and source.
     *
     * @param fileName the name of the file.
     * @param source the source of the file content.
     * @return an Optional containing the found S3File entity, or an empty Optional if no entity was found.
     */
    @Override
    public Optional<S3File> getByFileNameAndSource(String fileName, ContentSource source) {
        return s3FileRepository.findByFileNameAndSource(fileName, source);
    }
}