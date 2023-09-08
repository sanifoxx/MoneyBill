package com.moneybill.commondto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestEventCreateDto {

    private String ipAddress;
    private String requestUri;
    private LocalDateTime createdAt;
}
