package kz.baltabayev.identityservice.controller;

import jakarta.validation.Valid;
import kz.baltabayev.identityservice.model.dto.AuthRequest;
import kz.baltabayev.identityservice.model.dto.EmailMessageDto;
import kz.baltabayev.identityservice.model.dto.TokenResponse;
import kz.baltabayev.identityservice.model.dto.UserRequest;
import kz.baltabayev.identityservice.model.types.Role;
import kz.baltabayev.identityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final KafkaTemplate<String, EmailMessageDto> kafkaTemplate;

    @PostMapping("/register")
    ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest) {
        userService.register(userRequest);
        kafkaTemplate.send("email-sending-greeting-queue", new EmailMessageDto(
                userRequest.getMail(), "Hello, " + userRequest.getName() + "!"));
        return ResponseEntity.ok("User is saved!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(userService.authenticate(authRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/generate/{role}")
    public ResponseEntity<String> generate(@PathVariable String role) {
        return ResponseEntity.ok(userService.generateInviteCode(role.toUpperCase()));
    }
}
