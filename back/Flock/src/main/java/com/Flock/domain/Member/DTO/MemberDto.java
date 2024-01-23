package com.Flock.domain.Member.DTO;


import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Member.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MemberDto {
    Long id;

    String loginId;

    String password;

//    String phoneNumber;

    String memberName;

    String mail;

    String role;

    String gender;

    LocalDateTime createdAt;


    public MemberDto(Long id, String loginId, String password, String memberName, String mail, String role) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.memberName = memberName;
        this.mail = mail;
        this.role = role;
    }

    public static MemberDto from(Member member){
        return new MemberDto(
                member.getId(),
                member.getLoginId(),
                member.getPassword(),
//                member.getPhoneNumber(),
                member.getMemberName(),
                member.getMail(),
                member.getRole().name()
//                member.getGender().name(),
//                member.getCreatedAt()
        );
    }
}
