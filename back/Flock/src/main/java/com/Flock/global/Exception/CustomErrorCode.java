package com.Flock.global.Exception;

import org.springframework.http.HttpStatus;

public enum CustomErrorCode {
    CLUB_NOT_FOUND(HttpStatus.BAD_REQUEST,"존재하지 않는 클럽입니다.");

    HttpStatus status;
    String message;

    CustomErrorCode(HttpStatus status,String msg){
        this.status = status;
        this.message = msg;
    }
}
