package com.Flock.domain.Response;

import lombok.Getter;

@Getter
public class SingleResponse<T> extends CommonResponse {
    T data;
}
