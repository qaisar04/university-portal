package kz.baltabayev.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class JwtTokenUtils {

    @Value("${keycloak.keys}")
    public String secret;

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey signKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractName(String token) {
        return (String) extractAllClaims(token).get("name");
    }

    public String extractUsername(String token) {
        return (String) extractAllClaims(token).get("preferred_username");
    }

    public String extractEmail(String token) {
        return (String) extractAllClaims(token).get("email");
    }

    public List<String> extractRoles(String token) {
        Object realmAccessObj = extractAllClaims(token).get("realm_access");
        if (realmAccessObj instanceof Map) {
            Map<String, Object> realmAccess = (Map<String, Object>) realmAccessObj;
            Object rolesObj = realmAccess.get("roles");
            if (rolesObj instanceof List) {
                return (List<String>) rolesObj;
            }
        }
        return Collections.emptyList();
    }
}
