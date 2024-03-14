package kz.baltabayev.newsservice.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "news", schema = "develop")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Builder.Default
    @Column(name = "published_date")
    private LocalDateTime publishedDate = LocalDateTime.now();
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "news_tags", joinColumns = @JoinColumn(name = "news_id"))
    @Column(name = "tag")
    private List<String> tags;
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private Set<FileAttachment> files;
}
