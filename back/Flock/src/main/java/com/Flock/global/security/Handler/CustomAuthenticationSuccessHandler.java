package com.Flock.global.security.Handler;

import com.Flock.domain.Member.Entity.Role;
import com.Flock.global.security.config.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        boolean isExist = oAuth2User.getAttribute("exist");
        String role = oAuth2User.getAuthorities().stream().findFirst().orElseThrow(IllegalAccessError::new).getAuthority();

        // 회원이 존재하는 경우 jwt 토큰 발행
        if(isExist){
            String token = tokenProvider.createToken(email, Role.valueOf(role));
            log.info(token);

        }
        // 회원이 없으면 데이터베이스에 등록하고 jwt토큰 발행하고 로그인 인증 처리 시키기
        else {
            log.info("데이터베이스에 회원 insert");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
