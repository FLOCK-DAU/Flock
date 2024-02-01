package com.Flock.domain.Member.DTO;


import com.Flock.domain.Member.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    Long id;

//    String loginId;
//
//    String password;

//    String phoneNumber;

    String memberName;

    String mail;

    String role;

    String gender;

    Integer age_range;

    LocalDateTime createdAt;


    public MemberDto(Long id,  String memberName, String mail, String role, Integer age_range) {
        this.id = id;
//        this.loginId = loginId;
//        this.password = password;
        this.memberName = memberName;
        this.mail = mail;
        this.role = role;
        this.age_range = age_range;
    }

    public static MemberDto from(Member member){
        return new MemberDto(
                member.getId(),
//                member.getLoginId(),
//                member.getPassword(),
//                member.getPhoneNumber(),
                member.getMemberName(),
                member.getMail(),
                member.getRole().name(),
                member.getAge_range()
//                member.getGender().name(),
//                member.getCreatedAt()
        );
    }
}
