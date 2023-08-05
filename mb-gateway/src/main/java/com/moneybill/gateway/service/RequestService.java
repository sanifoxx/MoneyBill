package com.moneybill.gateway.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {

    ResponseEntity<Object> handleApiRequest(HttpServletRequest request);
}
