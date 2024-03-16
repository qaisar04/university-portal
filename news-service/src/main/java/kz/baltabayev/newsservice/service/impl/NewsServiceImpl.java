package kz.baltabayev.newsservice.service.impl;

import io.micrometer.core.annotation.Timed;
import kz.baltabayev.newsservice.client.StorageServiceClient;
import kz.baltabayev.newsservice.exception.NewsNotFoundException;
import kz.baltabayev.newsservice.model.entity.FileAttachment;
import kz.baltabayev.newsservice.model.entity.News;
import kz.baltabayev.newsservice.model.payload.FileUploadResponse;
import kz.baltabayev.newsservice.repository.FileAttachmentRepository;
import kz.baltabayev.newsservice.repository.NewsRepository;
import kz.baltabayev.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final StorageServiceClient storageServiceClient;
    private final FileAttachmentRepository fileAttachmentRepository;

    @Value("${aws.s3.bucket.content-bucket}")
    private String NEWS_CONTENT_SOURCE;

    @Override
    public News save(String title, String content, List<String> tags, List<MultipartFile> multipartFiles) {
        News news = News.builder()
                .title(title)
                .content(content)
                .tags(tags)
                .build();

        News savedNews = newsRepository.save(news);
        FileUploadResponse[] responses = storageServiceClient.upload(NEWS_CONTENT_SOURCE, savedNews.getId(), multipartFiles).getBody();
        Set<FileAttachment> fileAttachments = Arrays.stream(Objects.requireNonNull(responses))
                .map(r -> new FileAttachment(r.id(), r.source(), r.url(), savedNews))
                .map(fileAttachmentRepository::save)
                .collect(Collectors.toSet());

        savedNews.setFiles(fileAttachments);
        newsRepository.saveAndFlush(savedNews);
        return savedNews;
    }

    @Override
    public News update(Long id, String title, String content, List<String> tags) {
        News news = getById(id);
        news.setTitle(title);
        news.setContent(content);
        news.setTags(tags);

        return newsRepository.save(news);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll();
    }

    @Override
    public News getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.delete(getById(id));
    }
}
