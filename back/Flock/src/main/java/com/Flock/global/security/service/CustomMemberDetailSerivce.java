package com.Flock.global.security.service;

import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Member.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomMemberDetailSerivce implements UserDetailsService {

    @Autowired
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      log.info("시큐리티 서비스 진입");
        Optional<Member> member = memberRepository.findByUserName(username);
        if (!member.isPresent()){
            throw new UsernameNotFoundException("계정을 찾을 수 없습니다.");
        }
        return new MemberDetail(member.get());
    }


}
