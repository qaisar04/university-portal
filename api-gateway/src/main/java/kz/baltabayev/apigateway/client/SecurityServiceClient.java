package kz.baltabayev.apigateway.client;

import kz.baltabayev.apigateway.model.dto.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "security-service", path = "/api/v1/auth")
public interface SecurityServiceClient {

    @PostMapping
    ResponseEntity<Boolean> validateToken(@RequestBody TokenRequest token);
}
