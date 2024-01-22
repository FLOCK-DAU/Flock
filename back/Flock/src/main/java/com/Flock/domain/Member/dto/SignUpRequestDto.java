package com.Flock.domain.Member.DTO;

import lombok.Getter;
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

    String gender;
}
