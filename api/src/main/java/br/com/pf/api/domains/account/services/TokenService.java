package br.com.pf.api.domains.account.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@Log4j2
public class TokenService {

    @Value("${secret.key}")
    private String secretKey;

    public String generateToken(String email) {
        var instantNow = Instant.now();
        var tenMinutesInSeconds = 10 * 60;
        var expiresAt = instantNow.plus(tenMinutesInSeconds, ChronoUnit.SECONDS);
        return JWT.create()
                .withClaim("email", email)
                .withSubject(email)
                .withIssuedAt(instantNow)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String verifyToken(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token).getSubject();
    }
}
