package com.example.java.utils;

import com.example.java.entities.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.beans.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;

    private SecretKey getSignInKey() {
        byte[] secretKeyBytes = secretKey.getBytes();

        // Do secretKey có thể sẽ không chuyền về byte[] đúng yêu cầu nên phải mã hóa v Base64
        String secretKeyEncode = Encoders.BASE64.encode(secretKeyBytes);

        // Và từ secretKeyEncode sẽ chuyển về byte[] đúng yêu cầu cho hmacShaKey
        byte[] secretKeyDecode = Decoders.BASE64.decode(secretKeyEncode);

        // Để tạo một SecretKey sử dụng mảng byte của secretKey thì cần phải là mảng byte 64base
        return Keys.hmacShaKeyFor(secretKeyDecode);
    }

    public String generateToken(User user) {
        // Tạo Claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("user_id", user.getId());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuer("https://localhost:8088/") // Issuer cung cấp thông tin về địa chỉ (URL hoặc chuỗi định danh) của đơn vị tạo ra JWT. Nhằm xác định nguồn gốc của JWT và có thể hữu ích trong việc xác thực và kiểm tra tính toàn vẹn của JWT.
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(getSignInKey())
                .compact();

    }


    private Claims extractAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    public String getUsernameFromToken (String token) {
        return extractClaimFromToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired (String token) {
        return extractClaimFromToken(token, Claims::getExpiration).before(new Date());
    }

    public boolean validateToken(String token, User userDetails) {
        try {
            boolean isExpired = isTokenExpired(token);
            String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isExpired ;

        } catch (
                MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (
                ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (
                UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

}
