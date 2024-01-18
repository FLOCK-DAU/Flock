package com.Flock.domain.Member.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
    String loginId;
    String password;
}
