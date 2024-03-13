package kz.baltabayev.newsservice.exception;

public class NewsNotFoundException extends RuntimeException {
    public NewsNotFoundException(Long id) {
        super("News with id %s not found".formatted(id));
    }
}
