package com.Flock.domain.Member.Service;

import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Entity.Role;
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

import java.util.Optional;

@Slf4j
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

        Optional<Member> m = memberRepository.findByUserName(signUpRequestDto.getLoginId());

//        if (!m.isPresent()){
//            // 커스텀 예외처리 해주기 : 중복되는 아이디
//
//        }

        Member member = Member.builder()
                .loginId(signUpRequestDto.getLoginId())
                .password(encoder.encode(signUpRequestDto.getPassword()))
                .phoneNumber(signUpRequestDto.getPhoneNumber())
                .memberName(signUpRequestDto.getMemberName())
                .mail(signUpRequestDto.getMail())
                .role(Role.USER)
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
    public SingleResponse signIn(String loginId, String password) {
        Optional<Member> member = memberRepository.findByUserName(loginId);

        if (!encoder.matches(password, member.get().getPassword())) {
            throw new RuntimeException(); // 나중에 커스터마이징 하기
        }

        String token = jwtTokenProvider.createToken(member.get().getLoginId(), member.get().getRole());

        log.info(member.get().getRole().toString());
        return responseService.getSingleResponse(token);
    }

}
