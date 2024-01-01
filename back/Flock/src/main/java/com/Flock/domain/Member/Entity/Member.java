package com.Flock.domain.Member.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY)
    private Long id;

    // 로그인 아이디
    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private Integer phoneNumber;


    // 사용자명
    @Column(nullable = false)
    private String memberName;


    @Column(nullable = false)
    private String mail;




    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
