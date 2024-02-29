package kz.baltabayev.storageservice.repository;

import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface S3FileRepository extends JpaRepository<S3File, Long> {
    Optional<S3File> findByFileNameAndSource(String fileName, ContentSource sourse);
}
