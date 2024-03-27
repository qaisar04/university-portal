package kz.baltabayev.discoveryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 * This class is responsible for setting up the security configurations for the application.
 * It is annotated with @Configuration to indicate that it is a configuration class and
 * @EnableWebSecurity to enable Spring Security's web security support.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines a SecurityFilterChain bean.
     * The SecurityFilterChain is an interface that a 'chain of responsibility' pattern
     * implementation of Spring Security's security filters can be plugged into.
     *
     * @param httpSecurity an HttpSecurity instance to modify
     * @return the SecurityFilterChain bean
     * @throws Exception if an error occurs when configuring the HttpSecurity
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**"));
        return httpSecurity.build();
    }
}