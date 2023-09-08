package com.moneybill.mbstatisticsservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RequestEventInfoDto {

    private Long id;

    private String ipAddress;

    private String requestUri;

    private LocalDateTime createdAt;
}
