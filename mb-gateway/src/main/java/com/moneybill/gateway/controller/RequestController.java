package com.moneybill.gateway.controller;

import com.moneybill.gateway.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@RestController
public class RequestController {

    private final RequestService requestService;

    @RequestMapping(value = {"/api/v1/**", "/admin/**"})
    public ResponseEntity<Object> handleApiRequest(HttpServletRequest request) {
        log.info("{} {}{}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString() == null ? "" : "?" + request.getQueryString()
        );
        return requestService.handleApiRequest(request);
    }

    @RequestMapping(value = {"/api/v1/statistics/**", "/admin/statistics/**"})
    public ResponseEntity<Object> handleStatisticsRequest(HttpServletRequest request) {
        log.info("{} {}{}",
                request.getMethod(),
                request.getRequestURI(),
                request.getQueryString() == null ? "" : "?" + request.getQueryString()
        );
        return requestService.handleStatisticsRequest(request);
    }
}
