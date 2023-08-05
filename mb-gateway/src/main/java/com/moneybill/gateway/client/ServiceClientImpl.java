package com.moneybill.gateway.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ServiceClientImpl implements ServiceClient {

    private final RestTemplate restTemplate;

    public ServiceClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new IgnoreRestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public ResponseEntity<Object> sendRequest(String serviceUrl, HttpServletRequest request) {
        return restTemplate.exchange(
                serviceUrl + request.getRequestURI(),
                HttpMethod.valueOf(request.getMethod()),
                new HttpEntity<>(
                        extractBody(request),
                        extractHeaders(request)
                ),
                Object.class
        );
    }

    private HttpHeaders extractHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
    }

    private String extractBody(HttpServletRequest request) {
        try {
            return request.getReader()
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
