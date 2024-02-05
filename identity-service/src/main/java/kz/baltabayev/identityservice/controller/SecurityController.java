package kz.baltabayev.identityservice.controller;

import jakarta.validation.Valid;
import kz.baltabayev.identityservice.model.dto.AuthRequest;
import kz.baltabayev.identityservice.model.dto.TokenResponse;
import kz.baltabayev.identityservice.model.dto.UserRequest;
import kz.baltabayev.identityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;

    @GetMapping("/status")
    public String status() {
        return "Working";
    }

    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        return ResponseEntity.ok("User is saved!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }
}
