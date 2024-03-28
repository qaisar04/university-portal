package kz.baltabayev.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

/**
 * Configuration class for handling security within the application.
 * It uses Spring's WebFluxSecurity for reactive applications.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    /**
     * Bean for handling security within the application.
     * It configures the security filter chain for the application.
     * It allows all requests to "/actuator/health", "/actuator/health/**", "/eureka/*" without any authentication.
     * It uses OAuth2 Resource Server with JWT for authentication.
     * It disables CSRF protection.
     *
     * @param http ServerHttpSecurity object for configuring security
     * @return SecurityWebFilterChain object configured with the defined security properties
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
                .authorizeExchange(configurer -> configurer
                        .pathMatchers("/actuator/health", "/actuator/health/**", "/eureka/*").permitAll())
                .oauth2ResourceServer(configurer -> configurer.jwt(Customizer.withDefaults()))
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }
}