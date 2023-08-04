package com.moneybill.gateway.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
public class TestController {

    private final RestTemplate restTemplate;

    public TestController() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @RequestMapping("/api/v1/**")
    public ResponseEntity<Object> test1(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Collections.list(request.getHeaderNames()).forEach(name -> headers.add(name, request.getHeader(name)));
        System.out.println(headers);

        String body;
        try {
            body = request.getReader()
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            if (body.isBlank()) {
                headers.add("content-type", "application/json");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(headers);


        HttpEntity<Object> httpEntity = new HttpEntity<>(body, headers);

        System.out.println("http://localhost:8081" + request.getRequestURI());
        System.out.println(httpEntity.getBody());

        ResponseEntity<Object> response;
        try {
            response = restTemplate.exchange(
                    "http://localhost:8081" + request.getRequestURI(),
                    HttpMethod.valueOf(request.getMethod()),
                    httpEntity,
                    Object.class
            );
        } catch (HttpStatusCodeException e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("content-type", "application/json");
            response = ResponseEntity.status(e.getStatusCode())
                    .headers(responseHeaders)
                    .body(e.getResponseBodyAsString());
        }
        System.out.println(response);
        return response;
    }
}
