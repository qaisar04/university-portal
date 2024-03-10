package kz.baltabayev.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class JwtConfig {

    @Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}")
    public String issuerUri;


    @Bean
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri(issuerUri + "/.well-known/jwks.json")
                .webClient(WebClient.create())
                .build();
    }
}