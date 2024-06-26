package com.lcwd.user.service.payLoad;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private  String message;
    private  boolean success;
    private HttpStatus status;
}
