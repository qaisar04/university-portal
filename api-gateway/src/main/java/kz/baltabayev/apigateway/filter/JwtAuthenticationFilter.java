package kz.baltabayev.apigateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.baltabayev.apigateway.client.SecurityServiceClient;
import kz.baltabayev.apigateway.model.dto.TokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityServiceClient securityServiceClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null) {
            TokenRequest tokenRequest = new TokenRequest(token);
            ResponseEntity<Boolean> validationResponse = securityServiceClient.validateToken(tokenRequest);

            if (validationResponse.getBody() != null && validationResponse.getBody()) {
//                Authentication auth = token != null ? new AuthenticatedUser(token) : null;
//                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
