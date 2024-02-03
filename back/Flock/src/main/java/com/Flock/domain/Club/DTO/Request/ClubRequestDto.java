package com.Flock.domain.Club.DTO.Request;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "클럽 제목")
    String title;

    @Schema(description = "클럽 소개글")
    String introduce;

    @Schema(description = "클럽 최대인원 : 방장이 설정")
    Integer maximum;

    @Schema(description = "클럽 활동 요일 : 복수 입력", example = "[\"월요일\",\"금요일\",\"일요일\"]")
    List<String> activityDays;

    @Schema(description = "클럽 활동 주기 : Integer")
    Integer activityFrequency;

    @Schema(description = "클럽 공개 / 비공개 여부")
    Boolean secret;

    @Schema(description = "클럽 모집 하는 중 / 모집 안 함")
    Boolean isRecruitment;

    @Schema(description = "클럽 성별 ( value : 남자, 여자, 혼성)", example = "남자")
    String gender;

    @Schema(description = "클럽 카테고리 아이디")
    Integer categoryId;

    @Schema(description = "클럽 태그 : # 빼고 주면 됨", examples = {"스포츠","축구","여자만"})
    List<String> Tags;

}
