package com.Flock.domain.Likes.Service;

import com.Flock.domain.Likes.Entity.Likes;
import com.Flock.domain.Likes.Repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    public Integer getLikesCount(Long clubId){
        return likesRepository.countLikesByClub(clubId);
    }

    /**
     * 좋아요 추가 및 삭제 (데이터베이스에 있으면 삭제하고 없으면 추가하는 함수)
     */
    public Integer updateLikes(Long clubId, Long memberId){
        Optional<Likes> likes = likesRepository.findByClub_IdAndMember_Id(clubId,memberId);

        // 좋아요가 존재하면 삭제
        if(likes.isPresent()){
            likesRepository.delete(likes.get());
            return 0;
        }
        // 존재하지 않으면 좋아요 추가
        else{
            Integer bool = likesRepository.insertLikes(clubId, memberId);

            return bool;
        }
    }

}
