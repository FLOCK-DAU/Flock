package com.Flock.domain.Member.DTO;

import com.Flock.domain.Member.Entity.Enum.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    @Schema(description = "카카오 닉네임",example = "채승지")
    String memberName;

    @Schema(description = "사용자 입력 닉네임.",example = "똘복이")
    String nickname;

    @Schema(description = "카카오에서 받아온 메일")
    String mail;

    @Schema(description = "성별",example = "MALE")
    String gender;

    @Schema(description = "생년월일",example = "2000-07-18")
    LocalDate birthday;


}
