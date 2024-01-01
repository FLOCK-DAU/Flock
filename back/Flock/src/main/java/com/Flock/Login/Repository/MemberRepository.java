package com.Flock.Login.Repository;

import com.Flock.Login.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {



}
