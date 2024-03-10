package kz.baltabayev.authservice.service.impl;

import kz.baltabayev.authservice.model.dto.EmailMessageDto;
import kz.baltabayev.authservice.model.dto.StudentRequest;
import kz.baltabayev.authservice.model.dto.UserRequest;
import kz.baltabayev.authservice.model.entity.User;
import kz.baltabayev.authservice.model.types.Role;
import kz.baltabayev.authservice.repository.UserRepository;
import kz.baltabayev.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.queues.student}")
    private String studentQueue;

    @Value("${spring.kafka.queues.email}")
    private String emailSendingQueue;

    private void sendGreetingEmail(UserRequest userRequest) {
        kafkaTemplate.send(emailSendingQueue, new EmailMessageDto(
                userRequest.email(), "Hello, %s! Welcome to our service!".formatted(userRequest.name())));
    }

    private void checkRole(UserRequest userRequest, User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            kafkaTemplate.send(studentQueue, new StudentRequest(
                    userRequest.name(), userRequest.email()
            ));
        }
    }
}
