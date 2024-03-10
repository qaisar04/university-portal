package kz.baltabayev.authservice.model.dto;

public record StudentRequest(
        String name,
        String email
) {
}