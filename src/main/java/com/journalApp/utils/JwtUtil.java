package com.journalApp.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private String SECRET_KEY = "Tak+HaV^uvCHEFsEVfypW#7g9^k*Z8$V";
	
	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String username) {
		Map<String,Object> claims = new HashMap<>();
		return createToken(claims,username);
	}
	
	public String extractUsername(String token) {
		Claims claims =extractAllClaims(token);
		return claims.getSubject();
		
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public Boolean validateToken(String token) {
		
		return  !isTokenExpired(token);
		
	}
	
	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration (String token) {
		return extractAllClaims(token).getExpiration();
	}
	private String createToken(Map<String,Object> claims, String subject) {
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.header().empty().add("typ","JWT")
				.and()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
				.signWith(getSigningKey())
				.compact();
						
	}

}
