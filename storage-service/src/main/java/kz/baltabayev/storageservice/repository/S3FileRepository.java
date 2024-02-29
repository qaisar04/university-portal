package kz.baltabayev.storageservice.repository;

import kz.baltabayev.storageservice.model.entity.S3File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface S3FileRepository extends JpaRepository<S3File, Long> {
}
