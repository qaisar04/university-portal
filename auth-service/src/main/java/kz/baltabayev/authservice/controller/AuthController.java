package kz.baltabayev.authservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import kz.baltabayev.authservice.model.dto.AuthorizationResponse;
import kz.baltabayev.authservice.service.impl.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/**")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @RequestMapping
    public ResponseEntity<AuthorizationResponse> handleRequest(HttpServletRequest request) {
        String baererToken = request.getHeader("Authorization");
        String token = baererToken.substring(7);
        AuthorizationResponse response = tokenService.valid(token);
        return ResponseEntity.ok(response);
    }
}
