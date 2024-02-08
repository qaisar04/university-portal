package kz.baltabayev.invitationcodeservice.service;

import kz.baltabayev.invitationcodeservice.model.types.Role;

/**
 * Service interface for managing invite codes.
 */
public interface InviteCodeService {

    /**
     * Generates an invite code for the given role.
     *
     * @param role the role for which to generate an invite code
     * @return the generated invite code
     */
    String generateInviteCode(Role role);

    /**
     * Retrieves the role associated with the given invite code.
     *
     * @param code the invite code
     * @return the role associated with the invite code
     */
    Role getRoleByInviteCode(String code);
}