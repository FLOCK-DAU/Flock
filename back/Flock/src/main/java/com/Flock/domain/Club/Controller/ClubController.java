package com.Flock.domain.Club.Controller;

import com.Flock.domain.Club.DTO.ClubListDto;
import com.Flock.domain.Club.DTO.Request.ClubRequestDto;
import com.Flock.domain.Club.DTO.Response.ClubResponseDto;
import com.Flock.domain.Club.Service.ClubService;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ListResponse;
import com.Flock.domain.Response.ResponseService;
import com.Flock.domain.Response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "클럽 리스트 조회 : ", description = "카테고리아이디가 0이면 카테고리 상관없이 조회한다.")
    @GetMapping("/api/{categoryId}/clubs")
    public ListResponse getClubs(@PathVariable("categoryId") Long categoryId,
                                 @RequestParam(required = false, value = "title") String title,
                                 @RequestParam(required = false, value = "tag") String tag) {

        List<ClubListDto> clubListDtoList;

        if (categoryId == 0) {
            clubListDtoList = clubService.getClubs(title, tag);
        } else {
            clubListDtoList = clubService.getClubsWithCategoryId(categoryId, title, tag);
        }


        return responseService.getListResponse(clubListDtoList);
    }

    /**
     * 인기 Club 조회 (좋아요 1당 x a) + ....
     */


    /**
     * 클럽 최신 순 조회 Top 5
     */


    /**
     * Club 생성
     */
    @Operation(summary = "클럽 생성 : Swagger 페이지 하단의 ClubRequestDto 참고")
    @PostMapping("/api/club")
    public CommonResponse createClub(@RequestBody ClubRequestDto clubRequestDto, @AuthenticationPrincipal MemberDetail memberDetail) {

        Long memberId = memberDetail.getMember().getId();

        CommonResponse response = clubService.createClub(clubRequestDto, memberId);

        return response;
    }

    /**
     * Club 상세 조회
     */
    @GetMapping("/api/club")
    public SingleResponse getClubDetails(@RequestParam("clubId") Long clubId, @AuthenticationPrincipal MemberDetail memberDetail){

        Long memberId = memberDetail.getMember().getId();

        ClubResponseDto clubResponseDto = clubService.getClub(clubId,memberId);

        return responseService.getSingleResponse(clubResponseDto);
    }


    /**
     * Club 수정
     */


}
