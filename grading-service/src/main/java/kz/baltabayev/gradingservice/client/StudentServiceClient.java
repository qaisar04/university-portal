package kz.baltabayev.gradingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "student-service", url = "http//localhost:8080/students")
public interface StudentServiceClient {

    //todo: logic here
}
