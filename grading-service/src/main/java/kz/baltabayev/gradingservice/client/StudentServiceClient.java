package kz.baltabayev.gradingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "student-service", path = "/students")
public interface StudentServiceClient {

    //todo: logic here
}
