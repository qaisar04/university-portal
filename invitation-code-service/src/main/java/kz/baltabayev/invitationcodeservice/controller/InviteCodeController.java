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

/**
 * Controller for managing invite codes.
 * It provides endpoints for generating and using invite codes.
 *
 * @author Qaisar
 */
@RestController
@RequestMapping("api/v1/invite-code")
@RequiredArgsConstructor
public class InviteCodeController {

    /**
     * Service for managing invite codes.
     */
    public final InviteCodeService inviteCodeService;

    /**
     * Generates an invite code for the given role.
     *
     * @param role the role for which to generate an invite code
     * @return the generated invite code
     * @throws InvalidRoleException if the given role is not valid
     */
    @GetMapping("/{role}")
    public ResponseEntity<String> generate(@PathVariable String role) {
        try {
            return ResponseEntity.ok(inviteCodeService.generateInviteCode(Role.valueOf(role.toUpperCase())));
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException("Invalid role: " + role);
        }
    }

    /**
     * Uses the given invite code and returns the associated role.
     *
     * @param code the invite code to use
     * @return the role associated with the used invite code
     */
    @GetMapping("/link/{code}")
    public ResponseEntity<Role> useInviteCode(@PathVariable String code) {
        return ResponseEntity.ok(inviteCodeService.getRoleByInviteCode(code));
    }
}