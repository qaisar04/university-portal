package kz.baltabayev.invitationcodeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Entry point for the Invitation Code Service application.
 * This application is a Spring Boot application and is enabled for service discovery.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class InvitationCodeServiceApplication {

    /**
     * Main method which starts the Spring Boot application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(InvitationCodeServiceApplication.class, args);
    }
}