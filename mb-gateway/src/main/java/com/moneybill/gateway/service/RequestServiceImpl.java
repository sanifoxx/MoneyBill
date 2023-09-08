package com.moneybill.gateway.service;

import com.moneybill.commondto.RequestEventCreateDto;
import com.moneybill.gateway.client.ServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RequestServiceImpl implements RequestService {

    private final ServiceClient serviceClient;

    @Override
    public ResponseEntity<Object> handleApiRequest(HttpServletRequest request) {
        //TODO: add authorization
        RequestEventCreateDto requestEventCreateDto = RequestEventCreateDto.builder()
                .ipAddress(request.getRemoteAddr())
                .requestUri(ServiceClient.extractUriWithParams(request))
                .createdAt(LocalDateTime.now())
                .build();
        ResponseEntity<Object> response = serviceClient.sendHttpRequest(
                "http://localhost:8082/admin/statistics/request-events",
                HttpMethod.POST,
                null,
                requestEventCreateDto
        );
        System.out.println(1);
        System.out.println(response);

        ResponseEntity<Object> result = serviceClient.sendHttpRequest(
                "http://localhost:8081" + ServiceClient.extractUriWithParams(request),
                HttpMethod.valueOf(request.getMethod()),
                ServiceClient.extractHeaders(request),
                ServiceClient.extractBody(request)
        );
        System.out.println("1.1");
        System.out.println(result);

        return result;
    }

    @Override
    public ResponseEntity<Object> handleStatisticsRequest(HttpServletRequest request) {
        //TODO: add authorization
        RequestEventCreateDto requestEventCreateDto = RequestEventCreateDto.builder()
                .ipAddress(request.getRemoteAddr())
                .requestUri(ServiceClient.extractUriWithParams(request))
                .createdAt(LocalDateTime.now())
                .build();
        ResponseEntity<Object> response = serviceClient.sendHttpRequest(
                "http://localhost:8082/admin/statistics/request-events",
                HttpMethod.POST,
                null,
                requestEventCreateDto
        );
        System.out.println(2);
        System.out.println(response);

        return serviceClient.sendHttpRequest(
                "http://localhost:8082" + ServiceClient.extractUriWithParams(request),
                HttpMethod.valueOf(request.getMethod()),
                ServiceClient.extractHeaders(request),
                ServiceClient.extractBody(request)
        );
    }
}
