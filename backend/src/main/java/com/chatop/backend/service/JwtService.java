package com.chatop.backend.service;

import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties.Jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JwtService {

    private final JwtEncoder encoder;

    private final JwtDecoder decoder;

    public JwtService(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }


    public Jwt generateJwt(UserDetails userDetails) {
      return encoder.encode(JwtEncoderParameters.from(
        JwsHeader.from().build(),
        JwtClaimsSet.builder(String userDetails.getUsername(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(10)).build()
      ));
    }

    public Jwt verifyJwt(String jwt) {
      return decoder.decode(String jwt);
    }

}
