package com.Flock.domain.Club.DTO.Response;


import com.Flock.domain.Club.Entity.ClubMember;
import com.Flock.domain.Club.Entity.ClubTag;
import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubResponseDto {
    Long id;

    String title;

    String introduce;

    Integer maximum;

    LocalDateTime activityDate;

    List<String> activityDays;

    Integer activityFrequency;

    Boolean secret;

    Boolean isRecruitment;

    Gender gender;

    LocalDateTime createdAt;

    Member manager;

    List<ClubMember> clubMembers;

    List<ClubTag> clubTags;


}
