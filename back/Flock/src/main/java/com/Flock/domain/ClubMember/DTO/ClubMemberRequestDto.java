package com.Flock.domain.ClubMember.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubMemberRequestDto {

    Long clubId;
    // 가입신청하려는 사람의 멤버 아이디
    Long requestMemberId;

    Boolean isPermit;

}
