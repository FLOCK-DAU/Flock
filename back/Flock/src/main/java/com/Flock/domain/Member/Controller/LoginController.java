package com.Flock.domain.Member.Controller;

import com.Flock.domain.Member.Repository.MemberRepository;
import com.Flock.domain.Member.Service.MemberService;
import com.Flock.domain.Member.DTO.SignInRequestDto;
import com.Flock.domain.Member.DTO.SignUpRequestDto;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "로그인 컨트롤러",description = "로그인, 회원가입")
@RestController
public class LoginController {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Operation(summary = "로그인", description = "로그인 기능입니다.")
    @PostMapping("/api/sign-in")
    public SingleResponse signIn(@RequestBody SignInRequestDto sign){
        SingleResponse singleResponse = memberService.signIn(sign.getLoginId(), sign.getPassword());

        return singleResponse;
    }

    @Operation(summary = "회원가입")
    @PostMapping("/api/sign-up")
    public CommonResponse signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        CommonResponse response = memberService.signUp(signUpRequestDto);
        return response;
    }





    /**
     * admin 계정 생성
     */
//    @GetMapping("/make-admin")
//    @ResponseBody
//    public ResponseEntity<?> addAdmin(){
//
//        Member member = Member.builder()
//                .loginId("a")
//                .password(encoder.encode("1234"))
//                .phoneNumber("01064228462")
//                .memberName("채승지")
//                .gender(Gender.MALE)
//                .mail("1924245@donga.ac.kr")
//                .role(Role.USER)
//                .build();
//
//        memberRepository.save(member);
//
//        return ResponseEntity.ok(member);
//
//    }




}
