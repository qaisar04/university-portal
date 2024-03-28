package kz.baltabayev.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * This is the main application class for the Config Server.
 * It is annotated with @SpringBootApplication to indicate that it is a Spring Boot application.
 * It is also annotated with @EnableConfigServer to enable the Config Server functionality.
 * Additionally, it is annotated with @EnableDiscoveryClient to indicate that it is a discovery client.
 *
 * @Author qaisar
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerApplication {

    /**
     * This is the main method which serves as an entry point for the application.
     * It uses the SpringApplication.run() method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}