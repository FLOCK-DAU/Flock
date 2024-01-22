package com.Flock.domain.Member.Entity;

import com.Flock.domain.Member.DTO.MemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class MemberDetail implements UserDetails, OAuth2User {

    private MemberDto memberDto;

    private Map<String,Object> oAuth2Attribute;

    public MemberDetail(MemberDto memberDto){
        this.memberDto = memberDto;
        this.oAuth2Attribute = Map.of();
    }


    // oAuth 인증할 때 사용
    public MemberDetail(MemberDto memberDto,Map<String,Object> oAuth2Attribute){
        this.memberDto = memberDto;
        this.oAuth2Attribute = oAuth2Attribute;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(this.memberDto.getRole().toString()));
        return auth;

//        ArrayList<GrantedAuthority> auths = new ArrayList<>();
//        for(String role : member.getHasRole()){
//            auths.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return role;
//                }
//            });
//        }
//        return auths;

    }
    // 비밀번호 정보 제공
    @Override
    public String getPassword() {
        return memberDto.getPassword();
    }
    // ID 정보 제공
    @Override
    public String getUsername() {
        return memberDto.getLoginId();
    }
    // 계정 만료여부 제공
    // 특별히 사용을 안할시 항상 true를 반환하도록 처리
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 계정 비활성화 여부 제공
    // 특별히 사용 안할시 항상 true를 반환하도록 처리
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 계정 인증 정보를 항상 저장할지에 대한 여부
    // true 처리할시 모든 인증정보를 만료시키지 않기에 주의해야한다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 계정의 활성화 여부
    // 딱히 사용안할시 항상 true를 반환하도록 처리
    @Override
    public boolean isEnabled() {
        return true;
    }


    // OAuth에서 필요한 메소드

    @Override
    public String getName() {
        return memberDto.getLoginId();
    }

    /**
     * key value 값으로 어떤 값이든 받겠다
     */
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2Attribute;
    }

    public static MemberDetail from(MemberDto memberDto) {
        return new MemberDetail(memberDto);
    }
}
