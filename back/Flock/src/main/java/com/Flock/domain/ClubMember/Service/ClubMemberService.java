package com.Flock.domain.ClubMember.Service;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Repository.ClubRepository;
import com.Flock.domain.Club.Service.ClubService;
import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.ClubMember.Repository.ClubMemberRepository;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;

    private final MemberService memberService;

    private final ClubRepository clubRepository;

    public Integer countClubMember(Club club) {
        return clubMemberRepository.countClubMemberByClubAndIsMemberIsTrue(club.getId());
    }

    /**
     * 가입 신청
     */
    public Boolean joinClub(Long clubId, Long memberId) {
        Optional<Member> member = memberService.findById(memberId);
        Optional<Club> club = clubRepository.findById(clubId);


        // To do : CustomException ()
        if (member.isEmpty()) return false;
        if (club.isEmpty()) return false;

        Optional<ClubMember> presentClubMember = clubMemberRepository.findByClubAndMember(club.get(), member.get());

        // To do : CustomException (가입 신청이 완료되었습니다.)
        if(presentClubMember.isPresent()) return false;

        ClubMember clubMember = ClubMember.builder()
                .member(member.get())
                .club(club.get())
                .isMember(false)
                .build();

        clubMemberRepository.save(clubMember);
        return true;
    }

    /**
     * 가입 허가
     */

    /**
     *  방장의 회원 추방
     */

    /**
     * 클럽 나가기
     */
}
