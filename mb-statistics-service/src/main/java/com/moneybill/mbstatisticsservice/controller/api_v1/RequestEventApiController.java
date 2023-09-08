package com.moneybill.mbstatisticsservice.controller.api_v1;


import com.moneybill.mbstatisticsservice.service.RequestEventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/statistics")
public class RequestEventApiController {

    private final RequestEventService requestEventService;
}
