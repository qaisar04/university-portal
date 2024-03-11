package kz.baltabayev.authservice.security;

import jakarta.annotation.PostConstruct;
import kz.baltabayev.authservice.model.dto.TokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtException;

@Slf4j
@Configuration
public class TokenValidator {

    private JwtDecoder decoder;
    @Value("${keycloak.issuer-uri}")
    public String issuerUri;

    @PostConstruct
    public void init() {
        this.decoder = JwtDecoders.fromIssuerLocation(issuerUri);
    }

    public boolean validate(String token) {
        try {
            decoder.decode(token);
            return true;
        } catch (JwtException e) {
            log.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }
}
