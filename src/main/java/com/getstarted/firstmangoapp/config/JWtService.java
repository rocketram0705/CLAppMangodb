package com.getstarted.firstmangoapp.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWtService {

    private static final String SECRETE_KEY = "22f007f2826e0baed8ecfebf1557cee4219d58df07cd2f7142a523149a9db48a";
    public String extractUserCpsNo(String jwt) {
       return extractClaim(jwt,Claims::getSubject);
    } 

    public <T> T extractClaim(String jwt,Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims (String jwt){
        return Jwts.parserBuilder().setSigningKey(getSingInKey()).build().parseClaimsJws(jwt).getBody();
    }

    private Key getSingInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 24)).signWith(getSingInKey(),SignatureAlgorithm.HS256).compact();
    }
    //overloaded method 
    public String generateJwtToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(getSingInKey(),SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid (String jwt,UserDetails userDetails){
        final String userName = extractUserCpsNo(jwt);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
       return extractTokenExpiryTime(jwt).before(new Date());
    }

    private Date extractTokenExpiryTime(String jwt) {
        return extractClaim(jwt,Claims::getExpiration);
    }
}    