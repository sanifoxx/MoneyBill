package com.moneybill.gateway.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface ServiceClient {

    /**
     * Send http request to the service.
     * @param url url of the service
     * @param method http method of the request
     * @param headers http headers of the request
     * @param body http body of the request
     * @return http response from the service
     * @throws RuntimeException if service is unavailable
     */
    ResponseEntity<Object> sendHttpRequest(String url, HttpMethod method, HttpHeaders headers, Object body);

    /**
     * Send http request to the service.
     * @param url url of the service
     * @param method http method of the request
     * @param body http body of the request
     * @return http response from the service
     * @throws RuntimeException if service is unavailable
     */
    ResponseEntity<Object> sendHttpRequest(String url, HttpMethod method, Object body);

    /**
     * Send http request to the service.
     * @param url url of the service
     * @param method http method of the request
     * @return http response from the service
     * @throws RuntimeException if service is unavailable
     */
    ResponseEntity<Object> sendHttpRequest(String url, HttpMethod method);

    /**
     * Extract headers from request.
     * @param request http request
     * @return http headers
     */
    static HttpHeaders extractHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
    }

    /**
     * Extract body from request.
     * @param request http request
     * @return http body
     * @throws RuntimeException if reading from request fails
     */
    static String extractBody(HttpServletRequest request) {
        try {
            return request.getReader()
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extract params from request.
     * @param request http request
     * @return http params
     */
    static String extractParams(HttpServletRequest request) {
        String q = request.getQueryString();
        if (q == null) {
            return "";
        }
        return Arrays.stream(q.split("&"))
                .filter(elem -> !elem.isBlank())
                .filter(elem -> elem.split("=").length == 2)
                .collect(Collectors.joining("&"));
    }

    /**
     * Extract uri with params from request.
     * @param request - http request
     * @return uri with params
     */
    static String extractUriWithParams(HttpServletRequest request) {
        return request.getRequestURI() + "?" + extractParams(request);
    }
}
