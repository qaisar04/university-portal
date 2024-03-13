package kz.baltabayev.newsservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "file_attachment")
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String source;
    private String url;
    @ManyToOne
    private News news;

    public FileAttachment(String fileName, String source, String url, News news) {
        this.fileName = fileName;
        this.source = source;
        this.url = url;
        this.news = news;
    }
}
