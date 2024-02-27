package kz.baltabayev.storageservice.service.impl;

import kz.baltabayev.storageservice.entity.ImageData;
import kz.baltabayev.storageservice.repository.StorageRepository;
import kz.baltabayev.storageservice.service.StorageService;
import kz.baltabayev.storageservice.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    public String uploadImage(MultipartFile file) {
        ImageData image = null;
        try {
            image = storageRepository.save(ImageData.builder()
                    .name(file.getName())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image != null
                ? String.format("File upload successful: %s", file.getOriginalFilename())
                : String.format("File upload failed: %s", file.getOriginalFilename());
    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> imageData = storageRepository.findByName(fileName);
        if (imageData.isPresent()) {
            return ImageUtils.decompressImage(imageData.get().getImageData());
        }
        return new byte[0];
    }
}
