package com.Flock.global.Exception;

import org.springframework.http.HttpStatus;

public enum CustomErrorCode {
    CLUB_NOT_FOUND(HttpStatus.BAD_REQUEST,"존재하지 않는 클럽입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"존재하지 않는 사용자입니다."),

    APPLICANT_NOT_FOUND(HttpStatus.BAD_REQUEST,"존재하지 않는 신청자입니다."),
    PERMISSION_DENIED(HttpStatus.FORBIDDEN,"권한 거부됨");


    HttpStatus status;
    String message;

    CustomErrorCode(HttpStatus status,String msg){
        this.status = status;
        this.message = msg;
    }
}
