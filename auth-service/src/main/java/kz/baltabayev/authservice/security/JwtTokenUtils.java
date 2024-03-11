package kz.baltabayev.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class JwtTokenUtils {

    @Value("${keycloak.keys}")
    public String secret;

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private PublicKey getPublicKey() {
        try {
            String publicKeyContent = secret;
            publicKeyContent = publicKeyContent.replaceAll("\\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
            return kf.generatePublic(keySpecX509);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
