package com.Flock.domain.Member.Service;

import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.Member.DTO.MemberDto;
import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Entity.Enum.Role;
import com.Flock.domain.Member.Repository.MemberRepository;
import com.Flock.domain.Member.DTO.SignUpRequestDto;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ResponseService;
import com.Flock.domain.Response.SingleResponse;
import com.Flock.global.security.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ResponseService responseService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();




    /**
     * 회원가입
     */
    public CommonResponse signUp(SignUpRequestDto signUpRequestDto) {
        // 일단 회원가입하는 사용자는 모두 일반 사용자라고 하겠음

        Optional<Member> m = memberRepository.findByMail(signUpRequestDto.getMail());

//        if (!m.isPresent()){
//            // 커스텀 예외처리 해주기 : 중복되는 아이디
//
//        }

        Member member = Member.builder()
                .memberName(signUpRequestDto.getMemberName())
                .mail(signUpRequestDto.getMail())
                .role(Role.USER)
                .gender(Gender.valueOf(signUpRequestDto.getGender()))
                .birthday(signUpRequestDto.getBirthday())
                .build();

        memberRepository.save(member);

        CommonResponse response = new CommonResponse();
        response.setSuccessResponse();
        response.setMessage("정상적으로 등록되었습니다.");

        return response;
    }

    /**
     * 로그인
     */
//    public SingleResponse signIn(String email, String password) {
//        Optional<Member> member = memberRepository.findByUserName(email);
//
//        if (!encoder.matches(password, member.get().getPassword())) {
//            throw new RuntimeException(); // 나중에 커스터마이징 하기
//        }
//
//        String token = jwtTokenProvider.createToken(member.get().getMail(), member.get().getRole());
//
//        return responseService.getSingleResponse(token);
//    }

//    public Optional<MemberDto> searchMember(String loginId){
//        return memberRepository.findByUserName(loginId).map(MemberDto::from);
//    }

    public Optional<MemberDto> searchMemberByMail(String mail){
        return memberRepository.findByMail(mail).map(MemberDto::from);
    }


    /**
     * 카카오유저 회원가입에서 카카오에서 받은 정보로 일단 디비에 올리기
     */
//    public MemberDto saveMember(String loginId, String password, String memberName, String mail) {
//
//        Member member = Member.builder()
//                .loginId(loginId)
//                .password(encoder.encode(password))
//                .memberName(memberName)
//                .mail(mail)
//                .role(Role.USER)
//                .build();
//
//        return MemberDto.from(
//                memberRepository.save(member)
//        );
//
//    }

    public Optional<Member> findById(Long id){
        return memberRepository.findById(id);
    }

    public List<MemberDto> findByClubMemberId(List<ClubMember> clubMembers) {

        List<MemberDto> memberDtos = memberRepository.findByClubMemberId(clubMembers)
                .stream().map(MemberDto::from).collect(Collectors.toList());

        return memberDtos;


    }
}
