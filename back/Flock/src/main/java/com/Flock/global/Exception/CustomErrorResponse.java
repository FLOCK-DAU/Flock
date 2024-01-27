package com.Flock.global.Exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {
    private Integer status;
    private String errorName;
    private String errorMessage;
}
