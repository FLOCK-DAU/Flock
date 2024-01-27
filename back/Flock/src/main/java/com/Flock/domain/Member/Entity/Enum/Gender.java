package com.Flock.domain.Member.Entity.Enum;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자"), FEMALE("여자"), MIXED("혼성");

    String description;

    Gender(String description) {
        this.description = description;
    }

    public static Gender fromDescription(String description) {
        for (Gender gender : Gender.values()) {
            if (gender.getDescription().equals(description)) return gender;
        }
        throw new IllegalArgumentException("Gender와 일치하는 description이 아닙니다 : " + description);
    }


}