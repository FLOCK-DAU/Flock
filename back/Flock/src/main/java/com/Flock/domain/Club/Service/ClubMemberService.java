package com.Flock.domain.Club.Service;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;

    public Integer countClubMember(Club club){
        return clubMemberRepository.countClubMemberByClubAndIsMemberIsTrue(club);
    }
}
