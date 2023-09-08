package com.moneybill.mbstatisticsservice.util.mapper;

import com.moneybill.mbstatisticsservice.dto.RequestEventInfoDto;
import com.moneybill.mbstatisticsservice.model.RequestEvent;

public final class RequestEventMapper {

    public static RequestEventInfoDto toInfoDto(RequestEvent requestEvent) {
        return RequestEventInfoDto.builder()
                .id(requestEvent.getId())
                .requestUri(requestEvent.getRequestUri())
                .ipAddress(requestEvent.getIpAddress())
                .createdAt(requestEvent.getCreatedAt())
                .build();
    }
}
