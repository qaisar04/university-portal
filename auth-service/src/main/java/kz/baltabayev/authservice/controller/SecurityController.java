package kz.baltabayev.authservice.controller;

import kz.baltabayev.authservice.model.dto.TokenRequest;
import kz.baltabayev.authservice.service.impl.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> validToken(@RequestBody TokenRequest token) {
        return ResponseEntity.ok(tokenService.valid(token));
    }
}
