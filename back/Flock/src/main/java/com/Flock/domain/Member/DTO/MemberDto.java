package com.Flock.domain.Member.DTO;


import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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

    Gender gender;

    LocalDate birthday;

    LocalDateTime createdAt;


    public MemberDto(Long id, String memberName, String mail, Gender gender, String role, LocalDate birthday, LocalDateTime createdAt) {
        this.id = id;
//        this.loginId = loginId;
//        this.password = password;
        this.memberName = memberName;
        this.mail = mail;
        this.gender = gender;
        this.role = role;
        this.birthday = birthday;
        this.createdAt = createdAt;
    }

    public static MemberDto from(Member member){
        return new MemberDto(
                member.getId(),
//                member.getLoginId(),
//                member.getPassword(),
//                member.getPhoneNumber(),
                member.getMemberName(),
                member.getMail(),
                member.getGender(),
                member.getRole().name(),
                member.getBirthday(),
//                member.getGender().name(),
                member.getCreatedAt()
        );
    }
}
