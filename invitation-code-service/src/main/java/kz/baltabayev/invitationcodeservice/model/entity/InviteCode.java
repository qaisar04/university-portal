package kz.baltabayev.invitationcodeservice.model.entity;

import kz.baltabayev.invitationcodeservice.model.types.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing an invite code.
 * It has a code and a role associated with it.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InviteCode {

    /**
     * The invite code.
     */
    private String code;

    /**
     * The role associated with the invite code.
     */
    private Role role;
}