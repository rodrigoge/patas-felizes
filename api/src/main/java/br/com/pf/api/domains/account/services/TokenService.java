package br.com.pf.api.domains.account.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class TokenService {

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String accountId) {
        var secretKeyBytes = secretKey.getBytes();
        return Jwts
                .builder()
                .claim("accountId", accountId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(Keys.hmacShaKeyFor(secretKeyBytes))
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            var secretKeyBytes = secretKey.getBytes();
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKeyBytes)).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
