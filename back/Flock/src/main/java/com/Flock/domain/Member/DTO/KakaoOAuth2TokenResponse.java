package com.Flock.domain.Member.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class KakaoOAuth2TokenResponse {

    private String token_type;
    private String access_token;
    private String id_token;
    private Long expires_in;
    private String refresh_token;
    private Long refresh_token_expires_in;
    private String scope;
}
