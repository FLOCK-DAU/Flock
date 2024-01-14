package com.Flock.domain.Member.Entity.Enum;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남자"),FEMALE("여자");

    String description;

    Gender(String description){
        this.description = description;
    }
}