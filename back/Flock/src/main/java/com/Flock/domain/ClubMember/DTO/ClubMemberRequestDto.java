package com.Flock.domain.ClubMember.DTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubMemberRequestDto {

    @Schema(description = "신청하려는 클럽 아이디 / 현재 클럽 아이디")
    Long clubId;

    @Schema(description = "신청하는 멤버의 아이디 / 추방하려는 멤버의 아이디")
    Long requestMemberId;


}
