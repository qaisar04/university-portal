package kz.baltabayev.identityservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invite-code-service", url = "http://localhost:1465/invite-code")
public interface InviteCodeService {

    @GetMapping("/link/{code}")
    String useInviteCode(@PathVariable String code);

    @GetMapping("/{role}")
    ResponseEntity<String> generate(@PathVariable String role);
}
