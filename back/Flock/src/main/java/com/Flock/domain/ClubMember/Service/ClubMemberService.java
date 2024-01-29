package com.Flock.domain.ClubMember.Service;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Repository.ClubRepository;
import com.Flock.domain.ClubMember.DTO.ClubMemberRequestDto;
import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.ClubMember.Repository.ClubMemberRepository;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Service.MemberService;
import com.Flock.global.Exception.CustomErrorCode;
import com.Flock.global.Exception.CustomException;
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
     *  가입 허가
     */
    public boolean permitClub(Long clubId, Boolean isPermit, Long requestMemberId, Long memberId) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));
        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new CustomException(CustomErrorCode.CLUB_NOT_FOUND));

        Member applicant = memberService.findById(requestMemberId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.APPLICANT_NOT_FOUND));


        // 방장이 아닌 경우
        if(member.getId() != club.getManager().getId()) throw new CustomException(CustomErrorCode.PERMISSION_DENIED);

        Optional<ClubMember> clubMember = clubMemberRepository.findByClubAndMember(club,applicant);

        if(isPermit) {
            clubMember.get().setIsMember(true);
            return true;
        }

        return false;

    }


    /**
     *  방장의 회원 추방
     */

    public String expelClubMember(ClubMemberRequestDto clubMemberRequestDto, Long memberId) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        Club club = clubRepository.findById(clubMemberRequestDto.getClubId())
                .orElseThrow(()-> new CustomException(CustomErrorCode.CLUB_NOT_FOUND));

        Member targetMember = memberService.findById(clubMemberRequestDto.getRequestMemberId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.APPLICANT_NOT_FOUND));

        if(member.getId() != club.getManager().getId()) throw new CustomException(CustomErrorCode.PERMISSION_DENIED);

        Optional<ClubMember> clubMember = clubMemberRepository.findByClubAndMember(club,targetMember);

        clubMemberRepository.delete(clubMember.get());

        return targetMember.getMemberName();
    }


    /**
     * 클럽 나가기
     */
    public String leaveClubMember(Long clubId, Long memberId) {
        Member member = memberService.findById(memberId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        Club club = clubRepository.findById(clubId)
                .orElseThrow(()-> new CustomException(CustomErrorCode.CLUB_NOT_FOUND));

        Optional<ClubMember> clubMember = clubMemberRepository.findByClubAndMember(club,member);

        clubMemberRepository.delete(clubMember.get());

        return club.getTitle();

    }


}
