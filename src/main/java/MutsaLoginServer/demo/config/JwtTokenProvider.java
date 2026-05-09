package MutsaLoginServer.demo.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    // 테스트용 보안키 (최소 32바이트 이상 문자열 권장)
    private final String secret = "mutsa-secret-key-for-session-2026-05-04";
    private final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

    // 토큰 유효 시간 (1시간)
    private final long validityInMilliseconds = 3600000;

    // 토큰 생성 기능
    public String createToken(String username) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(username) // 담을 정보 (유저 아이디)
                .setIssuedAt(now)     // 발급 시간
                .setExpiration(validity) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 암호화 알고리즘
                .compact();
    }

    // 토큰에서 아이디 추출
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}