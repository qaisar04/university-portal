package kz.baltabayev.identityservice.service;

import kz.baltabayev.identityservice.exception.InvalidCredentialsException;
import kz.baltabayev.identityservice.exception.PasswordMismatchException;
import kz.baltabayev.identityservice.exception.UserAlreadyExistsException;
import kz.baltabayev.identityservice.mapper.UserMapper;
import kz.baltabayev.identityservice.model.dto.AuthRequest;
import kz.baltabayev.identityservice.model.dto.TokenResponse;
import kz.baltabayev.identityservice.model.dto.UserRequest;
import kz.baltabayev.identityservice.model.entity.User;
import kz.baltabayev.identityservice.model.types.Role;
import kz.baltabayev.identityservice.repository.UserRepository;
import kz.baltabayev.identityservice.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtils jwtTokenUtils;

    public void register(UserRequest userRequest) {
        validatePasswordConfirmation(userRequest.getPassword(), userRequest.getConfirmPassword());

        if (findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("The user with the specified username exists.");
        }

        if (findByUsername(userRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("The user with the specified email exists.");
        }

        User user = userMapper.toModel(userRequest);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private void validatePasswordConfirmation(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new PasswordMismatchException("Passwords don't match.");
        }
    }
}