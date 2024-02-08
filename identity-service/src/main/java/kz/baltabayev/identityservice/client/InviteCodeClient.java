package kz.baltabayev.identityservice.client;

import kz.baltabayev.identityservice.model.types.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invitation-code-service", path = "/invite-code")
public interface InviteCodeClient {

    @GetMapping("/link/{code}")
    ResponseEntity<Role> useInviteCode(@PathVariable String code);

    @GetMapping("/{role}")
    ResponseEntity<String> generate(@PathVariable String role);
}