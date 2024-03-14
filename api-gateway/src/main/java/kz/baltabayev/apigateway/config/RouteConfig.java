package kz.baltabayev.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Value("${spring.cloud.gateway.routes.discovery-server-url}")
    private String discoveryServerUrl;

    @Value("${spring.cloud.gateway.routes.student-server-url}")
    private String studentServerUrl;

    @Value("${spring.cloud.gateway.routes.grading-server-url}")
    private String gradeServerUrl;

    @Value("${spring.cloud.gateway.routes.grading-server-url}")
    private String newsServerUrl;

    @Value("${spring.cloud.gateway.routes.faculty-server-url}")
    private String facultyDepartmentServerUrl;

    @Bean
    RouteLocator routeLocator(
            RouteLocatorBuilder locatorBuilder
    ) {
        return locatorBuilder.routes()
                .route("student-service", r -> r.path("/api/v1/students/**")
                        .uri(studentServerUrl))
                .route("grading-service", r -> r.path("/api/v1/grades/**")
                        .uri(gradeServerUrl))
                .route("faculty-service", r -> r.path("/api/v1/faculty/**")
                        .uri(facultyDepartmentServerUrl))
                .route("department-service", r -> r.path("/api/v1/department/**")
                        .uri(facultyDepartmentServerUrl))
//                .route("news-service", r -> r.path("/api/v1/news/**")
//                        .uri(newsServerUrl))
                .route("discovery-service", r -> r.path("/eureka/web")
                        .uri(discoveryServerUrl))
                .build();
    }
}
