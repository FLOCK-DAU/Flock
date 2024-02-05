package com.Flock.domain.Member.Repository;

import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.Member.DTO.MemberDto;
import com.Flock.domain.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

//    @Query("select m from Member m where m.loginId = ?1")
//    Optional<Member> findByUserName(String loginId);

    @Query("select m from Member m where m.mail = ?1")
    Optional<Member> findByMail(String mail);


    @Query("SELECT m " +
            "FROM Member m JOIN FETCH m.clubMembers cm " +
            "WHERE cm in ?1 AND cm.isMember = true")
    List<Member> findByClubMemberId(List<ClubMember> clubMembers);
}
