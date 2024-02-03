package com.Flock.domain.Member.Controller;

import com.Flock.domain.Member.DTO.*;
import com.Flock.domain.Member.Repository.MemberRepository;
import com.Flock.domain.Member.Service.MemberService;
import com.Flock.domain.Member.Service.OAuth2Service;
import com.Flock.domain.Response.CommonResponse;
import com.Flock.domain.Response.ResponseService;
import com.Flock.domain.Response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@Tag(name = "로그인 컨트롤러",description = "로그인, 회원가입")
@RestController
@RequiredArgsConstructor
public class LoginController {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    OAuth2Service oAuth2Service;

    @Autowired
    ResponseService responseService;

//    @Operation(summary = "로그인", description = "로그인 기능입니다.")
//    @PostMapping("/api/sign-in")
//    public SingleResponse signIn(@RequestBody SignInRequestDto sign){
//        SingleResponse singleResponse = memberService.signIn(sign.getLoginId(), sign.getPassword());
//
//        return singleResponse;
//    }

    @Operation(summary = "회원가입")
    @PostMapping("/api/sign-up")
    public CommonResponse signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        CommonResponse response = memberService.signUp(signUpRequestDto);
        return response;
    }



    /**
     * 리액트에서 인가코드와 함께 요청하는 걸 받기
     */
    @PostMapping("/login/oauth2/code/kakao")
    public SingleResponse getAccessToken2(@RequestBody Map<String,Object> param){

        String code = param.get("code").toString();

        log.info("인가코드 : " + code);


        String kakao_access_token = oAuth2Service.getOAuthToken(code);


        KakaoProfile userInfo = oAuth2Service.getUserInfo(kakao_access_token);

        return oAuth2Service.getTokenResponse(userInfo);

        // 이제 데이터베이스 불러서 있는지 확인하고 jwt 생성하고 시큐리티 컨텍스트에 등록

//        return responseService.getSingleResponse(tokenResponse);
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
