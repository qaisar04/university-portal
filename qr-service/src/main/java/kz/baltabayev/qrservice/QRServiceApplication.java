package kz.baltabayev.qrservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * QrServiceApplication is the main entry point for the QR Service application.
 * It uses Spring Boot's SpringApplication.run() method to launch the application.
 *
 * @see <a href="https://github.com/youngAndMad/social-network/tree/master/qr-service">Source Code</a>
 */
@SpringBootApplication
public class QRServiceApplication {
    /**
     * The main method which serves as the entry point for the JVM.
     * @param args command line arguments passed to the application. Not currently used in this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(QRServiceApplication.class, args);
    }
}