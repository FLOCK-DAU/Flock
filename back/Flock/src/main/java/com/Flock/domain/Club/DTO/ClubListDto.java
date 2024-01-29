package com.Flock.domain.Club.DTO;

import com.Flock.domain.Club.Entity.Club;
import com.Flock.domain.Club.Entity.ClubTag;
import com.Flock.domain.Member.Entity.Enum.Gender;
import com.Flock.domain.Tag.Entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class ClubListDto {

    Long id;

    String title;

    Boolean secret;

    Boolean isRecruitment;

    Gender gender;

    Long memberCount;

    Long likesCount;

    List<String> tags;

//    public ClubListDto(Long id, String title, Boolean secret, Boolean isRecruitment, Gender gender) {
//        this.id = id;
//        this.title = title;
//        this.secret = secret;
//        this.isRecruitment = isRecruitment;
//        this.gender = gender;
//    }

    public ClubListDto(Club club, Long likesCount, Long memberCount, Object tags){
        this.id = club.getId();
        this.title = club.getTitle();
        this.secret = club.getSecret();
        this.isRecruitment = club.getIsRecruitment();
        this.gender = club.getGender();
        this.tags = Arrays.asList(((String) tags).split(","));

        this.likesCount = likesCount;
        this.memberCount = memberCount;
    }
}
