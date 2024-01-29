package com.Flock.domain.Club.Controller;

import com.Flock.domain.Club.DTO.ClubListDto;
import com.Flock.domain.Club.DTO.Request.ClubRequestDto;
import com.Flock.domain.Club.Service.ClubService;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ListResponse;
import com.Flock.domain.Response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubController {

    @Autowired
    ResponseService responseService;

    @Autowired
    ClubService clubService;


    /**
     * Club 리스트 조회
     */
    @GetMapping("/api/{categoryId}/clubs")
    public ListResponse getClubs(@PathVariable("categoryId") Long categoryId,
                                 @RequestParam(required = false, value = "title") String title,
                                 @RequestParam(required = false,value = "tag")String tag) {

        List<ClubListDto> clubListDtoList = clubService.getClubs();

        return responseService.getListResponse(clubListDtoList);
    }

    /**
     * 인기 Club 조회
     */

    /**
     * 클럽 최신 순 조회 Top 5
     */


    /**
     * Club 생성
     */
    @PostMapping("/api/club")
    public CommonResponse createClub(@RequestBody ClubRequestDto clubRequestDto, @AuthenticationPrincipal MemberDetail memberDetail) {

        Long memberId = memberDetail.getMember().getId();

        CommonResponse response = clubService.createClub(clubRequestDto, memberId);

        return response;
    }

    /**
     * Club 상세 조회
     */


    /**
     * Club 수정
     */


}
