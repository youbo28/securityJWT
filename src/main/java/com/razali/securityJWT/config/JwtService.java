package com.razali.securityJWT.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    private static final String SECRET_KEY = "5a28b80e29480ff07ec7655e2fb7c6bfafc63ef433c85b7c2bcffd8dca08e6cc62ec91ea82919c8b2f6ba43017783faae33394d5f92c246249902a43e761b681ccf422a2acfb7f23d6b3e139951e62a25c8416b8de2f9c29749893186d950827c63acbeb46d6ead54888bc97cb44e21dafc77b07d45e1d4a169e48fd366e8dcaf3fb186335d8192d896a22768f4982691ae7dc89429f053c020a38b1d7cfa602b93355e99b2d83c012f6afd9c6d098b3bcaffa167e79a434d4ee48f53709b26d40d2d940da4cf8f995aa89055bf0ca139a3263cc33a6e1fd71aea50144c81249b388e95dd357e365893438bcbaa41cbb0475026972bf3424b4192a2ac24687b1";

    public String extractUsername(String token) {
        return  extractClaim(token, Claims::getSubject);
    }
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignIngKey())
                .compact();
    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }
    public boolean isTokenValid(String token,UserDetails  userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignIngKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    private SecretKey getSignIngKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
