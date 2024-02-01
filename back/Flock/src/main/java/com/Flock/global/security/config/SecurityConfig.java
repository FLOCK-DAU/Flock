package com.Flock.global.security.config;

import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Member.Entity.Enum.Role;
import com.Flock.domain.Member.Service.MemberService;
import com.Flock.global.security.DTO.KakaoOAuth2ResponseDto;
import com.Flock.global.security.Handler.CustomAccessDeniedHandler;
import com.Flock.global.security.Handler.CustomAuthenticationEntryPoint;
import com.Flock.global.security.Handler.CustomAuthenticationFailureHandler;
import com.Flock.global.security.Handler.CustomAuthenticationSuccessHandler;
import com.Flock.global.security.service.CustomMemberDetailSerivce;
import com.Flock.global.security.service.CustomOAuth2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.UUID;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    private final CustomMemberDetailSerivce customMemberDetailSerivce;
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2Service customOAuth2Service;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    /**
     * Spring Security의 앞단 설정을 할수 있다.
     * debug, firewall, ignore등의 설정이 가능
     * 단 여기서 resource에 대한 모든 접근을 허용하는 설정할수도 있는데
     * 그럴경우 SpringSecuity에서 접근을 통제하는 설정은 무시해버린다.
     */

    /**
     * Spring Security 기능 설정을 할수 있다.
     * 특정 리소스에 접근하지 못하게 하거나 반대로 로그인, 회원가입 페이지외에 인증정보가 있어야
     * 접근할 수 있도록 설정할 수 있다.
     * 특정 리소스의 접근허용 또는 특정 권한 요구,로그인, 로그아웃, 로그인,로그아웃 성공시 Event
     * 등의 설정이 가능하다.
     */


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        log.info("필터체인 진입");
//        http
//                .csrf((csrf) -> csrf.disable())
//                .authorizeHttpRequests((authorizeHttpRequests) ->
//                        authorizeHttpRequests
//                                .requestMatchers("/**").permitAll()
//                                .requestMatchers("/login").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin((formLogin) -> {
//                    formLogin
//                            .loginPage("/login")
//                            .loginProcessingUrl("/login")
//                            .usernameParameter("loginId")
//                            .passwordParameter("password")
//                            .defaultSuccessUrl("/main")
//                            .permitAll();
//                });
//
//        // admin은 모든 접근에 대해 허락하고
//        // 게스트는 특정 url만 허락할 필요있음 : ex ) 검색, ...
//
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("필터체인 진입");
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                .requestMatchers("/**").permitAll()
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resource의 static폴더 내부 허용
                                        .requestMatchers("/v3/api-docs/**","/swagger-resources/**","/swagger-ui/**","/api-docs/**","/static/**").permitAll()
                                        .requestMatchers("/api/sign-in", "/test-login","/login/**").permitAll()
                                        .requestMatchers("/api/sign-up").permitAll()
                                        .requestMatchers(HttpMethod.POST,"/api/category").hasAuthority(Role.ADMIN.name())
                                        .anyRequest().authenticated()
                )
                // AccessDeniedHandler :  권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우 예외를 전달
                // AuthenticationEntryPoint : 인증과정에서 예외가 발생할 경웅 예외를 전달
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint()).accessDeniedHandler(new CustomAccessDeniedHandler())
                )
//                .oauth2Login(oAuth ->
//                        oAuth.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2Service))
//                        .failureHandler(customAuthenticationFailureHandler)
//                        .successHandler(customAuthenticationSuccessHandler)
//                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                ;


        // admin은 모든 접근에 대해 허락하고
        // 게스트는 특정 url만 허락할 필요있음 : ex ) 검색, ...

        return http.build();
    }


//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(MemberService memberService){
//
//        log.info("OAuth 서비스 진입");
//
//        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//
//        return userRequest -> {
//            OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//            KakaoOAuth2ResponseDto kakaoOAuth2Response = KakaoOAuth2ResponseDto.from(oAuth2User.getAttributes());
//
//
//            // 일단 카카오에서 받는 정보에는 loginId를 받아올 수 없다. 그래서 임의로 로그인아이디를 우리가 만들어준다.
//            String registrationId = userRequest.getClientRegistration().getRegistrationId(); // kakao
//            String providerId = String.valueOf(kakaoOAuth2Response.id()); // 이는 카카오에서 제공하는 Long타입의 고유값
//
//            String loginId = registrationId + "_" + providerId;
//            String dummyPassword = UUID.randomUUID().toString();
//
//            // DB에 유저가 있다면 ok, 아니라면 가입시켜야지
//
//            return memberService.findById(loginId)
//                    .map(MemberDetail::from)
//                    .orElseGet( () -> MemberDetail.from(memberService.saveMember(
//                            loginId,
//                            dummyPassword,
//                            kakaoOAuth2Response.nickname(),
//                            kakaoOAuth2Response.email())
//                    ));
//        };
//    }


}
