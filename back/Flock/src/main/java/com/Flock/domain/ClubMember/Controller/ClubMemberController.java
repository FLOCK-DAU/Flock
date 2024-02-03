package com.Flock.domain.ClubMember.Controller;

import com.Flock.domain.ClubMember.DTO.ClubMemberRequestDto;
import com.Flock.domain.ClubMember.Service.ClubMemberService;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "일반 회원의 클럽 가입 신청")
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
    @Operation(description = "방장의 멤버의 가입 신청 받기")
    @PostMapping("/api/club-member/permit")
    public CommonResponse permitClubMember(@RequestBody ClubMemberRequestDto clubMemberRequestDto, @AuthenticationPrincipal MemberDetail memberDetail) {
        if (!clubMemberService.permitClub(clubMemberRequestDto.getClubId(), clubMemberRequestDto.getIsPermit(),
                clubMemberRequestDto.getRequestMemberId(), memberDetail.getMember().getId()))
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
    @Operation(description = "방장이 클럽 멤버를 추방한다")
    @PostMapping("/api/club-member/expel")
    public CommonResponse expelClubMember(@RequestBody ClubMemberRequestDto clubMemberRequestDto, @AuthenticationPrincipal MemberDetail memberDetail){

        String targetMember = clubMemberService.expelClubMember(clubMemberRequestDto,memberDetail.getMember().getId());

        return new CommonResponse(targetMember + " 추방 성공");
    }


    /**
     * 클럽 나가기
     */
    @Operation(description = "클럽 멤버의 클럽 나가기")
    @PostMapping("/api/club-member/leave")
    public CommonResponse leaveClub(Long clubId, @AuthenticationPrincipal MemberDetail memberDetail){

        String clubTitle = clubMemberService.leaveClubMember(clubId,memberDetail.getMember().getId());

        return new CommonResponse(clubTitle + "을 나갔습니다.");
    }

}
