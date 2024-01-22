package com.Flock.global.security.service;

import com.Flock.domain.Member.DTO.MemberDto;
import com.Flock.domain.Member.Service.MemberService;
import com.Flock.global.security.DTO.KakaoOAuth2ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("CustomOAuth2Service loadUser 진입");

        OAuth2UserService<OAuth2UserRequest,OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 인증서버 아이디

        KakaoOAuth2ResponseDto kakaoOAuth2Response = KakaoOAuth2ResponseDto.from(oAuth2User.getAttributes());   // 인증서버로부터 받아온 회원정보

        Optional<MemberDto> findMember = memberService.searchMemberByMail(kakaoOAuth2Response.email()); // 이 방법으로 할 거면 이메일도 유니크하게 만들어야 함.

        Map<String,Object> memberAttribute = kakaoOAuth2Response.converToMap();


        // 회원이 우리 데이터베이스에 존재하지 않는 경우
        if(findMember.isEmpty()){
            log.info("회원 존재하지 않음");
            memberAttribute.put("exist",false);
            return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),memberAttribute,"email");
        }
        // 회원이 존재하는 경우
        else{
            log.info("회원존재");
            memberAttribute.put("exist",true);
            return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(findMember.get().getRole())),memberAttribute,"email");
        }

        /**
         * 여기서 에러나는 것이 아니라면 일단 SuccessHandler로 가고 에러가 나면 FailureHandler로 가는 듯
         */
    }
}
