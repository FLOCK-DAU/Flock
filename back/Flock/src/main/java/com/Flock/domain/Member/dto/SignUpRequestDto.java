package com.Flock.domain.Member.DTO;

import com.Flock.domain.Member.Entity.Enum.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    String memberName;

    String mail;

    String gender;

    LocalDate birthday;


}
