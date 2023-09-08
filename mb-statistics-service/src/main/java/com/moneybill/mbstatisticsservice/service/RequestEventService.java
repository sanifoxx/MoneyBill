package com.moneybill.mbstatisticsservice.service;

import com.moneybill.commondto.RequestEventCreateDto;
import com.moneybill.mbstatisticsservice.dto.RequestEventInfoDto;

import java.util.List;
import java.util.Map;

public interface RequestEventService {

    /**
     * Creates a request event in the database
     * @param requestEventCreateDto - the request event to be created
     */
    void createRequestEvent(RequestEventCreateDto requestEventCreateDto);

    /**
     * Gets a list of request events by parameters
     * @param params
     * @return a list of request events
     */
    List<RequestEventInfoDto> getRequestEventsByParams(Map<String, String> params);
}
