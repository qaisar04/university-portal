package kz.baltabayev.storageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the StorageService application.
 * It is annotated with @SpringBootApplication, indicating that it is a Spring Boot application.
 *
 * @author qaisar
 */
@SpringBootApplication
public class StorageServiceApplication {

    /**
     * The main method that starts the application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(StorageServiceApplication.class, args);
    }
}