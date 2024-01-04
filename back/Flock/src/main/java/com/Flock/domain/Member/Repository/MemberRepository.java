package com.Flock.domain.Member.Repository;

import com.Flock.domain.Member.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.loginId = ?1")
    Optional<Member> findByUserName(String loginId);

}
