package com.Flock.domain.ClubMember.Controller;

import com.Flock.domain.ClubMember.DTO.ClubMemberPermitDto;
import com.Flock.domain.ClubMember.Service.ClubMemberService;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClubMemberController {

    @Autowired
    ClubMemberService clubMemberService;

    @Autowired
    ResponseService responseService;

    /**
     * 가입 신청
     * 일단 가입신청을 하면 isMember false로 해서 ClubMember 테이블에  추가하자
     */

    @PostMapping("/api/clubs/{clubId}/applications")
    public CommonResponse applyClub(@PathVariable("clubId") Long clubId, @AuthenticationPrincipal MemberDetail memberDetail) {

        if (!clubMemberService.joinClub(clubId, memberDetail.getMember().getId())) {
            CommonResponse response = new CommonResponse();
            response.setFailResponse("가입신청 실패");
            return response;
        } else {
            return new CommonResponse("가입신청 성공");
        }
    }


    /**
     * 가입 신청 받기
     */
    @PostMapping("/api/club-member/permit")
    public CommonResponse permitClubMember(@RequestBody ClubMemberPermitDto clubMemberPermitDto, @AuthenticationPrincipal MemberDetail memberDetail) {
        if (!clubMemberService.permitClub(clubMemberPermitDto.getClubId(),clubMemberPermitDto.getIsPermit(),
                clubMemberPermitDto.getApplicantId(), memberDetail.getMember().getId()))
        {
            CommonResponse response = new CommonResponse();
            response.setFailResponse("가입 승인 실패");
            return response;
        }
        else{
            return new CommonResponse("가입 승인 성공");
        }
    }

    /**
     * 클럽 멤버 추방
     */

    /**
     * 클럽 나가기
     */
}
