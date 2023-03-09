package com.bhardwaj.MoodGuardian.security.jwt;

import com.bhardwaj.MoodGuardian.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${moodguardian.jwtSecret}")
    private String jwtSecret;

    @Value("${moodguardian.jwtExpirationMs}")
    private int jwtExpirationMs;


    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        SecretKey secretKey = getSigningKey(jwtSecret);

        return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public static SecretKey getSigningKey(String encodedKey) {
        byte[] keyBytes = Decoders.BASE64.decode(encodedKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey(jwtSecret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey(jwtSecret)).build().parseClaimsJws(authToken);
            return true;
        } catch (SecurityException e){
            logger.error("Invalid JWT Signature: {}", e.getMessage());
        } catch (MalformedJwtException e){
            logger.error("Invalid JWT Token: {}", e.getMessage());
        } catch (ExpiredJwtException e){
            logger.error("JWT Token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e){
            logger.error("JWT Token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
