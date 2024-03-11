package kz.baltabayev.apigateway.dto;

import java.util.List;

public record AuthorizationResponse(
        boolean isAuthorized,
        String username,
        List<String> roles
) {
}
