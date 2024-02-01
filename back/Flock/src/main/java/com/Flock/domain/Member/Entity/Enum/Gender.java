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

    public static Gender fromString(String gender){
        for(Gender gender1 : Gender.values()){
            if(gender1.name().equalsIgnoreCase(gender)) return gender1;

        }
        throw new IllegalArgumentException("Gender와 일치하는 성별이 아닙니다 : " + gender);
    }



}