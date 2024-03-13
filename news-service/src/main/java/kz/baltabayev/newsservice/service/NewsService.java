package kz.baltabayev.newsservice.service;

import kz.baltabayev.newsservice.model.entity.News;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {

    News save(String title, String content, List<String> tags, List<MultipartFile> multipartFiles);

    News update(Long id, String title, String content, List<String> tags);

    List<News> getAll();

    News getById(Long id);

    void deleteById(Long id);
}
