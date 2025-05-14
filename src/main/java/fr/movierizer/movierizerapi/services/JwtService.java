package fr.movierizer.movierizerapi.services;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;

    private final long jwtExpiration;
        
    private static final Logger log = LoggerFactory.getLogger(JwtService.class);


    // The secretkey and jwtExpiration are injected using @Value we can find here real value in the application.properties file
    public JwtService(@Value("${security.jwt.secret-key}") String secretKey,
                      @Value("${security.jwt.expiration-time}") long jwtExpiration) {
        this.secretKey = secretKey;
        this.jwtExpiration = jwtExpiration;
    }
    
    /**
     * Retrieves the username from the given JWT token.
     *
     * @param token the token to extract the username from
     * @return the username extracted from the token
     */

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a claim from the given JWT token using the provided claims resolver.
     *
     * @param token the token to extract the claim from
     * @param claimsResolver a function that takes the extracted claims and returns the desired claim
     * @return the extracted claim
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generates a JWT token containing the provided user details with no claims.
     * 
     * @param userDetails the user details to include in the JWT token
     * @return the generated JWT token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token containing the provided user details and additional claims.
     *
     * @param extraClaims a map of additional claims to include in the JWT token
     * @param userDetails the user details to include as the subject of the JWT token
     * @return the generated JWT token as a string
     */

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    /**
     * Returns the expiration time for the JWT token in milliseconds.
     * 
     * @return the JWT token expiration time in milliseconds
     */
    public long getExpirationTime() {
        return jwtExpiration;
    }

    /**
     * Builds a JWT token containing the provided user details and additional claims.
     * This token is sign with a secret key given by the getSignInKey method.
     *
     * @param extraClaims a map of additional claims to include in the JWT token
     * @param userDetails the user details to include as the subject of the JWT token
     * @param expiration the expiration time for the JWT token in milliseconds
     * @return the generated JWT token as a string
     */
    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
        log.info("CONSTRUCTION TOKEN");
        log.info("GETUSERNAME = " + userDetails.getUsername());
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith( SignatureAlgorithm.HS256, getSignInKey())
                .compact();
    }

    /**
     * Returns true if the provided token is valid and matches the provided user details,
     * false otherwise.
     *
     * @param token the token to validate
     * @param userDetails the user details to match
     * @return true if the token is valid and matches the provided user details, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Checks if the provided token is expired.
     * 
     * @param token the JWT token to check
     * @return true if the token is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token the JWT token from which to extract the expiration date
     * @return the expiration date of the token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from the given JWT token.
     * 
     * @param token the JWT token from which to extract all claims
     * @return the extracted claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build().parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Retrieves the secret key used for signing JWT tokens.
     * 
     * @return the secret key as a Key object
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
