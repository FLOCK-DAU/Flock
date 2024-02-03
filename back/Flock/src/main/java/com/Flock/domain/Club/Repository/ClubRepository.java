package com.Flock.domain.Club.Repository;

import com.Flock.domain.Club.DTO.ClubListDto;
import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    @Query("SELECT new com.Flock.domain.Club.DTO.ClubListDto(c, " +
            "(SELECT COUNT(l) FROM Likes l WHERE l.club = c), " +
            "(SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club = c AND cm.isMember = true), " +
            "(SELECT GROUP_CONCAT(t.tagName) FROM ClubTag ct JOIN ct.tag t WHERE ct.club = c)) " +
            "FROM Club c")
    List<ClubListDto> findAllClubs();

    @Query("SELECT c FROM Club c LEFT JOIN FETCH c.likes")
    List<Club> findAllWithLikesAndMembers();

    @Query("SELECT new com.Flock.domain.Club.DTO.ClubListDto(c, " +
            "(SELECT COUNT(l) FROM Likes l WHERE l.club = c), " +
            "(SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club = c AND cm.isMember = true), " +
            "(SELECT GROUP_CONCAT(t.tagName) FROM ClubTag ct JOIN ct.tag t WHERE ct.club = c)) " +
            "FROM Club c where c.title like concat('%', ?1 , '%')")
    List<ClubListDto> findClubsByTitle(String title);


    @Query("SELECT new com.Flock.domain.Club.DTO.ClubListDto(c, " +
            "(SELECT COUNT(l) FROM Likes l WHERE l.club = c), " +
            "(SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club = c AND cm.isMember = true), " +
            "(SELECT GROUP_CONCAT(t.tagName) FROM ClubTag ct JOIN ct.tag t WHERE ct.club = c)) " +
            "FROM Club c where c.clubTags = ?1")
    List<ClubListDto> findClubsByTag(ClubTag clubTag);


    @Query("SELECT new com.Flock.domain.Club.DTO.ClubListDto(c, " +
            "(SELECT COUNT(l) FROM Likes l WHERE l.club = c), " +
            "(SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club = c AND cm.isMember = true), " +
            "(SELECT GROUP_CONCAT(t.tagName) FROM ClubTag ct JOIN ct.tag t WHERE ct.club = c)) " +
            "FROM Club c where c.category.id = ?1")
    List<ClubListDto> findAllClubsWithCategory(Long categoryId);



    @Query("SELECT new com.Flock.domain.Club.DTO.ClubListDto(c, " +
            "(SELECT COUNT(l) FROM Likes l WHERE l.club = c), " +
            "(SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club = c AND cm.isMember = true), " +
            "(SELECT GROUP_CONCAT(t.tagName) FROM ClubTag ct JOIN ct.tag t WHERE ct.club = c)) " +
            "FROM Club c where c.title like concat('%', ?2 , '%') and c.category.id = ?1")
    List<ClubListDto> findClubsByTitleWithCategory(Long categoryId,String title);


    @Query("SELECT new com.Flock.domain.Club.DTO.ClubListDto(c, " +
            "(SELECT COUNT(l) FROM Likes l WHERE l.club = c), " +
            "(SELECT COUNT(cm) FROM ClubMember cm WHERE cm.club = c AND cm.isMember = true), " +
            "(SELECT GROUP_CONCAT(t.tagName) FROM ClubTag ct JOIN ct.tag t WHERE ct.club = c)) " +
            "FROM Club c where c.clubTags = ?2 and c.category.id = ?1")
    List<ClubListDto> findClubsByTagWithCategory(Long categoryId, ClubTag clubtag);
}
