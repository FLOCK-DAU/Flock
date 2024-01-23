package com.Flock.domain.Member.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Builder
public class TokenResponse {
    String token;
    String email;
    String nickname;
}
