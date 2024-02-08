package kz.baltabayev.identityservice.model.payload;

public record StudentRequest(
        String name,
        String email
) {
}
