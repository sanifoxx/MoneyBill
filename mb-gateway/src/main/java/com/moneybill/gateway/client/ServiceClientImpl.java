package com.moneybill.gateway.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceClientImpl implements ServiceClient {

    private final RestTemplate restTemplate;

    public ServiceClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new IgnoreRestTemplateResponseErrorHandler())
                .build();
    }

    //TODO create a new Throw
    @Override
    public ResponseEntity<Object> sendHttpRequest(String url, HttpMethod method, HttpHeaders headers, Object body) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);
        try {
            return restTemplate.exchange(url, method, requestEntity, Object.class);
        } catch (ResourceAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<Object> sendHttpRequest(String url, HttpMethod method, Object body) {
        return sendHttpRequest(url, method, null, body);
    }

    @Override
    public ResponseEntity<Object> sendHttpRequest(String url, HttpMethod method) {
        return sendHttpRequest(url, method, null, null);
    }
}
