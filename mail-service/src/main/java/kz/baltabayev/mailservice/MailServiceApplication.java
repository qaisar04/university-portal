package kz.baltabayev.mailservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MailServiceApplication is the entry point of the Spring Boot application.
 * It uses the @SpringBootApplication annotation, which is a convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'kz.baltabayev.mailservice' package.
 */
@SpringBootApplication
public class MailServiceApplication {

    /**
     * The main method uses Spring Boot’s SpringApplication.run() method to launch an application.
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

}