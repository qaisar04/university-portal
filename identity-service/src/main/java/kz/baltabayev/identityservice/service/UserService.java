package kz.baltabayev.identityservice.service;

import kz.baltabayev.identityservice.client.InviteCodeClient;
import kz.baltabayev.identityservice.client.StudentServiceClient;
import kz.baltabayev.identityservice.exception.InvalidCredentialsException;
import kz.baltabayev.identityservice.exception.PasswordMismatchException;
import kz.baltabayev.identityservice.exception.UserAlreadyExistsException;
import kz.baltabayev.identityservice.mapper.UserMapper;
import kz.baltabayev.identityservice.model.dto.AuthRequest;
import kz.baltabayev.identityservice.model.dto.EmailMessageDto;
import kz.baltabayev.identityservice.model.dto.TokenResponse;
import kz.baltabayev.identityservice.model.dto.UserRequest;
import kz.baltabayev.identityservice.model.entity.User;
import kz.baltabayev.identityservice.model.payload.StudentRequest;
import kz.baltabayev.identityservice.model.types.Role;
import kz.baltabayev.identityservice.repository.UserRepository;
import kz.baltabayev.identityservice.utils.JwtTokenUtils;
import kz.baltabayev.loggingstarter.annotations.LoggableInfo;
import kz.baltabayev.loggingstarter.annotations.LoggableTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@LoggableInfo
@LoggableTime(name = "UserService")
public class UserService {

    private final UserRepository userRepository;
    private final StudentServiceClient studentServiceClient;
    private final InviteCodeClient inviteCodeClient;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Value("${spring.kafka.queues.student}")
    private String studentQueue;

    @Value("${spring.kafka.queues.email}")
    private String emailSendingQueue;

    public void register(UserRequest userRequest) {
        validateUserDetails(userRequest);

        User user = userMapper.toUser(userRequest);
        String inviteCode = userRequest.getInviteCode();
        if (!inviteCode.isBlank() && !inviteCode.isEmpty()) {
            user.setRole(inviteCodeClient.useInviteCode(inviteCode).getBody());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        sendGreetingEmail(userRequest);
        saveStudentIfRoleIsStudent(userRequest, user);

        userRepository.save(user);
    }

    public TokenResponse authenticate(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
            );
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        String token = jwtTokenUtils.generateToken(userDetails);

        return new TokenResponse(token);
    }

    public String generateInviteCode(String role) {
        return inviteCodeClient.generate(role).getBody();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePasswordConfirmation(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Passwords don't match.");
        }
    }

    private void validateUserDetails(UserRequest userRequest) {
        if (findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("The user with the specified username exists.");
        }

        if (findByUsername(userRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("The user with the specified email exists.");
        }

        validatePasswordConfirmation(userRequest.getPassword(), userRequest.getConfirmPassword());
    }

    private void sendGreetingEmail(UserRequest userRequest) {
        kafkaTemplate.send(emailSendingQueue, new EmailMessageDto(
                userRequest.getEmail(), "Hello, " + userRequest.getName() + "! Welcome to our service!"));
    }

    private void saveStudentIfRoleIsStudent(UserRequest userRequest, User user) {
        if (user.getRole() != null && user.getRole().equals(Role.STUDENT)) {
            kafkaTemplate.send(studentQueue, new StudentRequest(
                    userRequest.getName(), userRequest.getEmail()
            ));
        }
    }
}