package com.Flock.domain.Club.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubRequestDto {

    String title;

    String introduce;

    Integer maximum;

    List<String> activityDays;

    Integer activityFrequency;

    Boolean secret;

    Boolean isRecruitment;

    String gender;

    List<String> Tags;

}
