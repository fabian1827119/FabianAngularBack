package com.fsantillan.apirest.Services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.fsantillan.apirest.common.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    // private static final String SECRET_KEY="01234567899876543210";

    @Override
    public String getToken(User user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String,Object> extraClaims, User user) {
        return Jwts
            .builder()
            .claims(extraClaims)
            ///Se agregan claims personalizados
            .claim("userId", user.getId())
            .claim("firstName", user.getNames())
            .claim("lastName", user.getLastName())
            .subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getKey())
            .compact();
    }

    private SecretKey getKey() {
       byte[] keyBytes=Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username=getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }

    private Claims getAllClaims(String token)
    {
        return Jwts
            .parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token)
    {
        return getExpiration(token).before(new Date());
    }

    // private void secureRandom(){
    //     SecureRandom random = new SecureRandom();
    //     // byte[] keyBytes = new byte[256];
    //     byte[] keyBytes = new byte[32];
    //     random.nextBytes(keyBytes);
    //     String key = Base64.getEncoder().encodeToString(keyBytes);
    //     System.out.println(key);
    // }
    
}
