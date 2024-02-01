package com.Flock.domain.Member.Entity.Enum;

import lombok.Getter;

@Getter
public enum Role {
    USER("일반사용자"),ADMIN("관리자"),GUEST("게스트");

    private String description;

    Role(String description){
        this.description = description;
    }
}
