package kz.baltabayev.newsservice.controller;

import kz.baltabayev.newsservice.model.entity.News;
import kz.baltabayev.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getAll() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @PostMapping
    public ResponseEntity<News> create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam("tags") List<String> tags,
            @RequestParam("files") List<MultipartFile> multipartFiles
    ) {
        return ResponseEntity.ok(newsService.save(title, content, tags, multipartFiles));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<News> update(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam("tags") List<String> tags
    ) {
        return ResponseEntity.ok(newsService.update(id, title, content, tags));
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> get(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        newsService.deleteById(id);
        return ResponseEntity.noContent().build(); // http code 204
    }
}
