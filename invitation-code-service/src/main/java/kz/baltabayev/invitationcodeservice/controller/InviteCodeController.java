package kz.baltabayev.invitationcodeservice.controller;

import kz.baltabayev.invitationcodeservice.exception.InvalidRoleException;
import kz.baltabayev.invitationcodeservice.model.types.Role;
import kz.baltabayev.invitationcodeservice.service.InviteCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invite-code")
@RequiredArgsConstructor
public class InviteCodeController {

    public final InviteCodeService inviteCodeService;

    @GetMapping("/{role}")
    public ResponseEntity<String> generate(@PathVariable String role) {
        try {
            return ResponseEntity.ok(inviteCodeService.generateInviteCode(Role.valueOf(role.toUpperCase())));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("Invalid role: " + role);
        }
    }

    @GetMapping("/link/{code}")
    public ResponseEntity<Role> useInviteCode(@PathVariable String code) {
        return ResponseEntity.ok(inviteCodeService.getRoleByInviteCode(code));
    }
}
