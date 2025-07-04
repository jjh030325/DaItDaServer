package io.github.jjh030325.daitdaserver.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "my_very_secure_random_secret_key_1234567890";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    public String generateToken(Long id, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token); return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getId(String token) {
        return Long.parseLong(getClaims(token).getSubject());
    }

    public String getUserRole(String token) {
        return getClaims(token).get("role", String.class);
    }
}
