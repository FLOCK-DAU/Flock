package com.Flock.domain.Member.Entity;

import com.Flock.domain.ClubMember.Entity.ClubMember;
import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // 로그인 아이디
//    @Column(nullable = false)
//    private String loginId;
//
//    @Column(nullable = false)
//    private String password;

//    @Column(nullable = false, unique = true)
//    private String phoneNumber;


    // 사용자명
    private String memberName;

    private String mail;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Integer age_range;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "member")
    private List<ClubMember> clubMembers;


}
