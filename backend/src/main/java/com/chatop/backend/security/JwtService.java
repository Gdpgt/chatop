package com.chatop.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder encoder;
    private final Duration tokenValidity;

    public JwtService(JwtEncoder encoder, @Value("${jwt.validity}") Duration tokenValidity) {
        this.encoder = encoder;
        this.tokenValidity = tokenValidity;
    }

    
    public String generateJwt(String userEmail) {
        Instant now = Instant.now();
        return encoder.encode(JwtEncoderParameters.from(
            JwsHeader.with(MacAlgorithm.HS256).build(),
            JwtClaimsSet.builder()
                .subject(userEmail)
                .issuedAt(now)
                .expiresAt(now.plus(tokenValidity))
                .build()
        )).getTokenValue();
    }

}
