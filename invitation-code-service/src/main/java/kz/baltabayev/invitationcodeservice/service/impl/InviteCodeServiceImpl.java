package kz.baltabayev.invitationcodeservice.service.impl;

import com.google.common.hash.Hashing;
import kz.baltabayev.invitationcodeservice.model.types.Role;
import kz.baltabayev.invitationcodeservice.service.InviteCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class InviteCodeServiceImpl implements InviteCodeService {

    private final RedisTemplate<String, Role> redisTemplate;

    @Override
    public String generateInviteCode(Role role) {
        //todo: add validation
        String code = generateCode();
        redisTemplate.opsForValue().set(code, role);
        redisTemplate.expire(code, 24, TimeUnit.HOURS);
        return code;
    }

    @Override
    public Role getRoleByInviteCode(String code) {
        Role role = redisTemplate.opsForValue().get(code);
        redisTemplate.delete(code);
        return role;
    }

    private String generateCode() {
        UUID uuid = UUID.randomUUID();
        byte[] hash = Hashing.sha256().hashString(uuid.toString(), StandardCharsets.UTF_8).asBytes();
        String base64Encoded = Base64.getUrlEncoder().encodeToString(hash);
        int desiredLength = 8;
        return base64Encoded.substring(0, desiredLength).toUpperCase();
    }
}
