package com.Flock.domain.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {
    String loginId;

    String password;

    String phoneNumber;

    String memberName;

    String mail;
}
