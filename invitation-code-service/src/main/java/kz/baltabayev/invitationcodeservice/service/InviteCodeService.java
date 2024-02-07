package kz.baltabayev.invitationcodeservice.service;

import kz.baltabayev.invitationcodeservice.model.types.Role;

public interface InviteCodeService {

    String generateInviteCode(Role role);

    Role getRoleByInviteCode(String code);
}
