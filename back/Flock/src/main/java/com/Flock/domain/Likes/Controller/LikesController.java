package com.Flock.domain.Likes.Controller;

import com.Flock.domain.Likes.Service.LikesService;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ResponseService;
import com.Flock.domain.Response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikesController {

    @Autowired
    LikesService likesService;

    @Autowired
    ResponseService responseService;

    /**
     * Club 좋아요 업데이트
     */
    @GetMapping("/api/likes")
    public CommonResponse updateLikes(Long clubId, @AuthenticationPrincipal MemberDetail memberDetail){

        int status = likesService.updateLikes(clubId, memberDetail.getMember().getId());

        if(status == 0){
            return new CommonResponse("좋아요 삭제");
        }

        return new CommonResponse("좋아요 추가");
        
    }




}
