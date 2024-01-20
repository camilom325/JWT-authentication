package com.example.ToDoBack.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final byte[] KEY = "t5Ku7xR9yP2sH4mD1oL8vZ3cX6aF0qBwA7Hgst693jhdher6TyT".getBytes();
    private final Key secretKey = Keys.hmacShaKeyFor(KEY);

    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * Generates a JWT token with the provided extra claims and user details.
     *
     * @param extraClaim The extra claims to include in the token.
     * @param user       The user details for whom the token is being generated.
     * @return The generated JWT token.
     */
    private String getToken(Map<String, Object> extraClaim, UserDetails user) {
        return Jwts.builder()
                .setClaims(extraClaim)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Retrieves the username from the given JWT token.
     *
     * @param token the JWT token
     * @return the username extracted from the token
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * Validates a JWT token by comparing the username extracted from the token with
     * the username from the UserDetails object
     * and checking if the token is expired.
     *
     * @param token       The JWT token to validate.
     * @param userDetails The UserDetails object containing the user information.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isExpired(token));
    }

    /**
     * Retrieves all the claims from a JWT token.
     *
     * @param token the JWT token
     * @return the claims extracted from the token
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves the claim value from the given token using the provided claims
     * resolver function.
     *
     * @param <T>            the type of the claim value
     * @param token          the JWT token
     * @param claimsResolver the function to resolve the claims
     * @return the claim value
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Retrieves the expiration date from the given token.
     *
     * @param token the JWT token
     * @return the expiration date of the token
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * Checks if the given JWT token is expired.
     *
     * @param token The JWT token to check.
     * @return true if the token is expired, false otherwise.
     */
    private boolean isExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}
