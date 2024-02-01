package com.Flock.domain.Member.Service;

import com.Flock.domain.Member.DTO.KakaoOAuth2TokenResponse;
import com.Flock.domain.Member.DTO.KakaoProfile;
import com.Flock.domain.Member.DTO.MemberDto;
import com.Flock.domain.Member.DTO.TokenResponse;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Entity.Enum.Role;
import com.Flock.domain.Member.Repository.MemberRepository;
import com.Flock.domain.Response.ResponseService;
import com.Flock.domain.Response.SingleResponse;
import com.Flock.global.security.config.JwtTokenProvider;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2Service {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String client_secret;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    @Autowired
    MemberService memberService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private MemberRepository memberRepository;

    private final ResponseService responseService;



    public String getOAuthToken(String code){
        String reqUrl = "https://kauth.kakao.com/oauth/token";

        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", KAKAO_REDIRECT_URL);
        params.add("code", code);

        //http 바디(params)와 http 헤더(headers)를 가진 엔티티
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //reqUrl로 Http 요청 , POST 방식
        ResponseEntity<String> response =
                rt.exchange(reqUrl, HttpMethod.POST, kakaoTokenRequest, String.class);

        String responseBody = response.getBody();

        Gson gson = new Gson();
        KakaoOAuth2TokenResponse tokenResponse = gson.fromJson(responseBody, KakaoOAuth2TokenResponse.class);

        return tokenResponse.getAccess_token();
    }

    public KakaoProfile getUserInfo(String kakaoAccessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";

        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoAccessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //http 헤더(headers)를 가진 엔티티
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        //reqUrl로 Http 요청 , POST 방식
        ResponseEntity<String> response =
                rt.exchange(reqUrl, HttpMethod.POST, kakaoProfileRequest, String.class);

        KakaoProfile kakaoProfile = new KakaoProfile(response.getBody());

        return kakaoProfile;
    }

    public SingleResponse getTokenResponse(KakaoProfile userInfo){
        Optional<MemberDto> memberDto = memberService.searchMemberByMail(userInfo.getEmail());

        // 회원이 우리 데이터베이스에 존재하는 경우
        if (memberDto.isPresent()){
            log.info("회원존재");
            String token = jwtTokenProvider.createToken(memberDto.get().getMail(), Role.valueOf(memberDto.get().getRole()));
            TokenResponse tokenResponse = TokenResponse.builder()
                    .token(token)
                    .email(memberDto.get().getMail())
                    .nickname(memberDto.get().getMemberName())
                    .isMember(true)
                    .msg("가입되어 있는 회원입니다.")
                    .build();
            return responseService.getSingleResponse(tokenResponse);
        }
        // 회원이 우리 데이터베이스에 존재하지 않는 경우 : 자동으로 회원가입 시키는 로직 추가
        else {
            return responseService.getSingleResponse(userInfo);
        }
    }
}
