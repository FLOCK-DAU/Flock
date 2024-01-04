package com.Flock.global.security.config;

import com.Flock.domain.Member.Entity.MemberDetail;
import com.Flock.domain.Member.Entity.Role;
import com.Flock.global.security.service.CustomMemberDetailSerivce;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CustomMemberDetailSerivce serivce;

    @Value("${jwt.secret}")
    private String secretKey = "secretKey";

    private final long tokenValidMilliSecond = 1000L * 60 *60;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String userId, Role role){
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("role",role);

        Date now = new Date();

        // token 생성
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliSecond))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();

        return token;
    }


    /**
     *  필터에서 인증이 성공했을 때 SecurityContextHolder에 Authentication을 생성하는 역할
     *
     */
    public Authentication getAuthentication(String token){
        // 일단 임의로 업캐스팅 된 채로 두는데 후에 멤버에 대한 상세한 정보가 필요하면 MemberDetail로 바꿔야 함
        UserDetails memberDetail = serivce.loadUserByUsername(this.getUserName(token));

        return new UsernamePasswordAuthenticationToken(memberDetail,"",memberDetail.getAuthorities());
    }

    public String getUserName(String token) {
        String info = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

        return info;
    }

    /**
     * 클라이언트가 헤더를 통해 어플리케이션 서버로 JWT 토큰 값을 전달해야 됨!
     */
    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }

    /**
     * 토큰 유효기간 체크
     */
    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        }
        catch (Exception e){
            return false;
        }
    }



}
