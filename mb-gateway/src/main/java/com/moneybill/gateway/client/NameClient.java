package com.moneybill.gateway.client;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface NameClient {

    ResponseEntity<Object> sendRequest(String serviceUrl, HttpServletRequest request);
}
