package com.Flock.domain.ClubMember.Repository;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubMemberRepository extends JpaRepository<ClubMember,Long> {
    @Query("SELECT cm.club.id, COUNT(cm) FROM ClubMember cm WHERE cm.club.id IN :clubIds AND cm.isMember = TRUE GROUP BY cm.club.id")
    List<Object[]> countMembersByClubIds(@Param("clubIds") List<Long> clubIds);

    @Query("select count(cm) from ClubMember cm where cm.club.id = ?1 and cm.isMember=false")
    Integer countClubMemberByClubAndIsMemberIsTrue(Long clubId);

    @Query("select cm from ClubMember cm where cm.club=?1 and cm.member = ?2")
    Optional<ClubMember> findByClubAndMember(Club club, Member member);
}
