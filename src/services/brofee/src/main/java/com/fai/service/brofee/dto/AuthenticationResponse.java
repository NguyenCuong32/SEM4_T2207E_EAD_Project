package com.fai.service.brofee.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private String accessToken;

    private Instant expiresAt;

    private String email;

}
