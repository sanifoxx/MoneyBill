package com.moneybill.gateway.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ServiceClientImpl serviceClient;

    @Test
    @DisplayName("Should throw a runtime exception when there is a resource access exception")
    void sendHttpRequestWhenResourceAccessExceptionThenThrowRuntimeException() {
        String url = "http://example.com/api";
        HttpMethod method = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Object body = null;

        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);
        when(restTemplate.exchange(eq(url), eq(method), eq(requestEntity), eq(Object.class)))
                .thenThrow(ResourceAccessException.class);

        assertThrows(RuntimeException.class, () -> serviceClient.sendHttpRequest(url, method, headers, body));

        verify(restTemplate, times(1)).exchange(eq(url), eq(method), eq(requestEntity), eq(Object.class));
    }

    @Test
    @DisplayName("Should return response entity when the request is successful")
    void sendHttpRequestWhenRequestIsSuccessful() {
        String url = "http://example.com/api";
        HttpMethod method = HttpMethod.GET;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Object requestBody = new Object();

        ResponseEntity<Object> expectedResponse = ResponseEntity.ok().build();

        when(restTemplate.exchange(eq(url), eq(method), any(HttpEntity.class), eq(Object.class)))
                .thenReturn(expectedResponse);

        ResponseEntity<Object> actualResponse = serviceClient.sendHttpRequest(url, method, headers, requestBody);

        assertEquals(expectedResponse, actualResponse);
        verify(restTemplate, times(1)).exchange(eq(url), eq(method), any(HttpEntity.class), eq(Object.class));
    }

}