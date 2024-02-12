package kz.baltabayev.subjectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SubjectServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubjectServiceApplication.class, args);
    }
}
