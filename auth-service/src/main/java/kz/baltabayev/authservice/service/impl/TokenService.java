package kz.baltabayev.authservice.service.impl;

import kz.baltabayev.authservice.model.dto.TokenRequest;
import kz.baltabayev.authservice.security.JwtTokenUtils;
import kz.baltabayev.authservice.security.TokenValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenValidator tokenValidator;
    private final JwtTokenUtils jwtTokenUtils;

    public Boolean valid(TokenRequest token) {
        return tokenValidator.validate(token);
    }
}
