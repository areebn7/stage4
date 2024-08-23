package com.example.stage4.Security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet; 
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * Service for generating JSON Web Tokens (JWTs) and signing them with a private key.
 * The JWTs include claims such as the username and authorities of the authenticated user.
 */
@Service
public class TokenService {

    @Value("${rsa.private-key}")
    private RSAPrivateKey privateKey;

    @Value("${rsa.public-key}")
    private RSAPublicKey publicKey;

    /**
     * Generates a JWT for the given authentication.
     * 
     * Creates a JWT with claims including the issuer, issued time, expiration time, 
     * subject (username), and scope (authorities). The JWT is signed using the private key.
     * 
     * @param authentication The authentication object containing user details and authorities.
     * @return The generated JWT as a string.
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        // Create a space-delimited string containing all of the principal's authorities.
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    
        // Build a set of claims to go inside the JWT.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        // The NimbusJwtEncoder creates and encodes the JWT from the claims and signs it using the private key.
        return encoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * Creates a JwtEncoder that uses the RSA private key for signing and the RSA public key for verification.
     * 
     * @return The configured JwtEncoder.
     */
    private JwtEncoder encoder() {
        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
}
