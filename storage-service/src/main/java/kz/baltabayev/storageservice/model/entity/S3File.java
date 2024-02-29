package kz.baltabayev.storageservice.model.entity;

import jakarta.persistence.*;
import kz.baltabayev.storageservice.model.types.ContentSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class S3File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private LocalDateTime uploadedTime;
    @Enumerated(EnumType.STRING)
    private ContentSource source;
    private Long target;

    @PrePersist
    public void prePersist() {
        this.uploadedTime = LocalDateTime.now();
    }
}
