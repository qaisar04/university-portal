package kz.baltabayev.authservice.service.impl;

import kz.baltabayev.authservice.model.dto.AuthorizationResponse;
import kz.baltabayev.authservice.model.dto.TokenRequest;
import kz.baltabayev.authservice.security.JwtTokenUtils;
import kz.baltabayev.authservice.security.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenValidator tokenValidator;
    private final JwtTokenUtils jwtTokenUtils;

    public AuthorizationResponse valid(String token) {
        boolean validate = tokenValidator.validate(token);
        String username = jwtTokenUtils.extractUsername(token);
        List<String> roles = jwtTokenUtils.extractRoles(token);
        return new AuthorizationResponse(validate, username, roles);
    }
}
