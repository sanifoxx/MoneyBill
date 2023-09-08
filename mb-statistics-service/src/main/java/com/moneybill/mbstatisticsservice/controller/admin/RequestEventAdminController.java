package com.moneybill.mbstatisticsservice.controller.admin;

import com.moneybill.commondto.RequestEventCreateDto;
import com.moneybill.mbstatisticsservice.dto.RequestEventInfoDto;
import com.moneybill.mbstatisticsservice.service.RequestEventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("admin/statistics")
public class RequestEventAdminController {

    private final RequestEventService requestEventService;

    @PostMapping("/request-events")
    public void createRequestEvent(@RequestBody RequestEventCreateDto requestEventCreateDto) {
        requestEventService.createRequestEvent(requestEventCreateDto);
    }

    // params: page, count, ip, sortDirection, sortBy
    @GetMapping("/request-events")
    public List<RequestEventInfoDto> getRequestEventsByParams(@RequestParam Map<String, String> params) {

        return requestEventService.getRequestEventsByParams(params);
    }
}
