package com.Flock.domain.Likes.Repository;

import com.Flock.domain.Likes.Entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Long> {

    @Query("select count(l) from Likes l where l.club.id = ?1")
    Integer countLikesByClub(Long clubId);

    @Query("select l from Likes l where l.club.id = ?1 and l.member.id = ?2")
    Optional<Likes> findByClub_IdAndMember_Id(Long clubId, Long memberId);

    @Modifying
    @Query(value = "insert into likes(club_club_id, member_member_id) values(?1, ?2)",nativeQuery = true)
    Integer insertLikes(Long clubId, Long memberId);
}
