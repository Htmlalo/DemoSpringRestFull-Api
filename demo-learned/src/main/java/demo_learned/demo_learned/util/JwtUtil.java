package demo_learned.demo_learned.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private final String secretKey = "5uaT7bQ84LIiWH5XZkDQTDEiS02ZM23pVvFvvy8XhKDuPuBxviGOGfqezOIFHJyG";

    private final long EXPIRATION_TIME = TimeUnit.SECONDS.toMillis(30);

    private final long REFRESH_TOKEN_EXPIRATION_TIME = TimeUnit.HOURS.toMillis(1);

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer("Vanthuat")
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public String generateRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .withIssuer("Vanthuat")
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(secretKey));
    }



    public String extractUsername(String token) {
        DecodedJWT decodedJWT = decodeTokenWithoutVerification(token);
        if (decodedJWT != null) return decodedJWT.getSubject();
        return null;
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).withIssuer("Vanthuat").build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private DecodedJWT decodeTokenWithoutVerification(String token) {
        try {
            return JWT.decode(token); // Chỉ decode, không xác minh
        } catch (Exception e) {
            return null; // Không decode được
        }
    }
}
