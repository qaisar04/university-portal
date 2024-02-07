package kz.baltabayev.invitationcodeservice.model.entity;

import kz.baltabayev.invitationcodeservice.model.types.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InviteCode {
    private String code;
    private Role role;
}
