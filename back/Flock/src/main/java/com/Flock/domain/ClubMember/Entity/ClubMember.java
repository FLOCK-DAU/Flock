package com.Flock.domain.ClubMember.Entity;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Member.Entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;


    // 클럽 멤버인 사람 / 가입 신청 중인 사람
    private Boolean isMember;
}
