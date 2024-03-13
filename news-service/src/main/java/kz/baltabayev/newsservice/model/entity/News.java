package kz.baltabayev.newsservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @ElementCollection
    private List<String> tags;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private Set<FileAttachment> files;
}
