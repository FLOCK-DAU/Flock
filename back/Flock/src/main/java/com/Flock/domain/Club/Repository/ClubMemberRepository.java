package com.Flock.domain.Club.Repository;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember,Long> {
    @Query("SELECT cm.club.id, COUNT(cm) FROM ClubMember cm WHERE cm.club.id IN :clubIds AND cm.isMember = TRUE GROUP BY cm.club.id")
    List<Object[]> countMembersByClubIds(@Param("clubIds") List<Long> clubIds);

    Integer countClubMemberByClubAndIsMemberIsTrue(Club club);
}
