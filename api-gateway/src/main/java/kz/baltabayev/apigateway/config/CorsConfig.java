package kz.baltabayev.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.Arrays;

/**
 * Configuration class for handling Cross-Origin Resource Sharing (CORS) within the application.
 * Extends the CorsConfiguration class and implements the WebFluxConfigurer interface.
 */
@Configuration
public class CorsConfig extends CorsConfiguration implements WebFluxConfigurer {

    // Flag to determine if cookies and credentials are allowed for CORS
    @Value("${cors.allowCredentials}")
    private boolean allowCredentials;

    // Array of allowed origins for CORS
    @Value("${cors.allowedOrigins}")
    private String[] allowedOrigins;

    // Array of allowed HTTP methods for CORS
    @Value("${cors.allowedMethods}")
    private String[] allowedMethods;

    // Array of allowed HTTP headers for CORS
    @Value("${cors.allowedHeaders}")
    private String[] allowedHeaders;

    /**
     * Bean for handling CORS within the application.
     * Configures allowed origins, methods, and headers based on the properties defined above.
     *
     * @return CorsWebFilter object configured with the defined CORS properties
     */
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(allowCredentials);
        corsConfiguration.setAllowedOrigins(Arrays.stream(allowedOrigins).toList());
        corsConfiguration.setAllowedMethods(Arrays.stream(allowedMethods).toList());
        corsConfiguration.setAllowedHeaders(Arrays.stream(allowedHeaders).toList());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }
}