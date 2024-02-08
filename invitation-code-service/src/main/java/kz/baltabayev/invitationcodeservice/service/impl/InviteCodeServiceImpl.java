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

/**
 * Service implementation for managing invite codes.
 */
@Service
@RequiredArgsConstructor
public class InviteCodeServiceImpl implements InviteCodeService {

    /**
     * Redis template for storing and retrieving roles associated with invite codes.
     */
    private final RedisTemplate<String, Role> redisTemplate;

    /**
     * Generates an invite code for the given role and stores it in Redis.
     *
     * @param role the role for which to generate an invite code
     * @return the generated invite code
     */
    @Override
    public String generateInviteCode(Role role) {
        //todo: add validation
        String code = generateCode();
        redisTemplate.opsForValue().set(code, role);
        redisTemplate.expire(code, 24, TimeUnit.HOURS);
        return code;
    }

    /**
     * Retrieves the role associated with the given invite code from Redis and deletes the code.
     *
     * @param code the invite code
     * @return the role associated with the invite code
     */
    @Override
    public Role getRoleByInviteCode(String code) {
        Role role = redisTemplate.opsForValue().get(code);
        redisTemplate.delete(code);
        return role;
    }

    /**
     * Generates a unique code by hashing a random UUID and encoding it in base64.
     *
     * @return the generated code
     */
    private String generateCode() {
        UUID uuid = UUID.randomUUID();
        byte[] hash = Hashing.sha256().hashString(uuid.toString(), StandardCharsets.UTF_8).asBytes();
        String base64Encoded = Base64.getUrlEncoder().encodeToString(hash);
        int desiredLength = 8;
        return base64Encoded.substring(0, desiredLength).toUpperCase();
    }
}