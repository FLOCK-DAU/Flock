package com.Flock.domain.Club.DTO.Response;


import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.Enum.DayOfWeek;
import com.Flock.domain.Member.DTO.MemberDto;
import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Member.Entity.Member;
import com.Flock.domain.Tag.DTO.TagDto;
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

    List<DayOfWeek> activityDays;

    Integer activityFrequency;

    Boolean secret;

    Boolean isRecruitment;

    Gender gender;

    LocalDateTime createdAt;

    MemberDto manager;

    List<MemberDto> clubMembers;

    List<TagDto> tags;

    Boolean isManager = false;



    public ClubResponseDto(Club club, List<MemberDto> clubMembers, List<TagDto> tags, Boolean isManager) {
        this.id = club.getId();
        this.title = club.getTitle();
        this.introduce = club.getIntroduce();
        this.maximum = club.getMaximum();
        this.activityDate = club.getActivityDate();
        this.activityDays = club.getActivityDays();
        this.activityFrequency = club.getActivityFrequency();
        this.secret = club.getSecret();
        this.isRecruitment = club.getIsRecruitment();
        this.gender = club.getGender();
        this.createdAt = club.getCreatedAt();
        this.manager = MemberDto.from(club.getManager());
        this.clubMembers = clubMembers;
        this.tags = tags;
        this.isManager = isManager;
    }
}
