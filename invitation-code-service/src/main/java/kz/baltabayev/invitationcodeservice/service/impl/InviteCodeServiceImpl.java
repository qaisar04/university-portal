package kz.baltabayev.invitationcodeservice.service.impl;

import kz.baltabayev.invitationcodeservice.model.types.Role;
import kz.baltabayev.invitationcodeservice.service.InviteCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class InviteCodeServiceImpl implements InviteCodeService {

    private final RedisTemplate<String, Role> redisTemplate;

    @Override
    public String generateInviteCode(Role role) {
        //todo: add validation for code
        String code = generateCode();
        redisTemplate.opsForValue().set(code, role);
        redisTemplate.expire(code, 24, TimeUnit.HOURS);
        return code;
    }

    @Override
    public Role getRoleByInviteCode(String code) {
        return redisTemplate.opsForValue().get(code);
    }

    private String generateCode() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
