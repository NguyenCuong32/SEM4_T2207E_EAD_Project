package com.fai.service.brofee.service;

import com.fai.service.brofee.entity.Role;
import com.fai.service.brofee.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtEncoder jwtEncoder;

    @Getter
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    public String generateToken(Authentication authentication) {
        // CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
                .subject(authentication.getName())
//                .claim("scope", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")))
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateToken(User user) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
                .subject(user.getEmail())
                .claim("scope", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(" ")))
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
