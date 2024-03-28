package kz.baltabayev.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * This is the main application class for the Discovery Service.
 * It is annotated with @SpringBootApplication to indicate that it is a Spring Boot application.
 * It is also annotated with @EnableEurekaServer to enable the Eureka Server for service discovery.
 *
 * @Author qaisar
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

    /**
     * This is the main method which serves as an entry point for the application.
     * It uses the SpringApplication.run() method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceApplication.class, args);
    }
}