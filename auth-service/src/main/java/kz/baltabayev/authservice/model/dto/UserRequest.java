package kz.baltabayev.authservice.model.dto;

public record UserRequest(
        String name,
        String username,
        String email,
        String password
) {
}
