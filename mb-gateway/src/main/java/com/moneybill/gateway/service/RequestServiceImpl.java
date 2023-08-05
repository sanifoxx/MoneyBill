package com.moneybill.gateway.service;

import com.moneybill.gateway.client.ServiceClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RequestServiceImpl implements RequestService {

    private final ServiceClient serviceClient;

    @Override
    public ResponseEntity<Object> handleApiRequest(HttpServletRequest request) {
        //TODO: add authorization
        return serviceClient.sendRequest("http://localhost:8081", request);
    }
}
