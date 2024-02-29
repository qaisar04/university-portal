package kz.baltabayev.storageservice.service.impl;

import kz.baltabayev.storageservice.model.entity.S3File;
import kz.baltabayev.storageservice.model.types.ContentSource;
import kz.baltabayev.storageservice.repository.S3FileRepository;
import kz.baltabayev.storageservice.service.S3FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3FileServiceImpl implements S3FileService {

    private final S3FileRepository s3FileRepository;

    @Override
    public S3File save(S3File s3File) {
        return s3FileRepository.save(s3File);
    }

    @Override
    public void delete(Long id) {
        s3FileRepository.deleteById(id);
    }

    @Override
    public Optional<S3File> getByFileNameAndSource(String fileName, ContentSource source) {
        return s3FileRepository.findByFileNameAndSource(fileName, source);
    }
}
