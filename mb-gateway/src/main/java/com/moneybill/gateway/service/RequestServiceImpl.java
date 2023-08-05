package com.moneybill.gateway.service;

import com.moneybill.gateway.client.NameClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class RequestServiceImpl implements RequestService {

    private final NameClient nameClient;

    @Override
    public void test(HttpServletRequest request) {
        nameClient.sendRequest("", request);
    }
}
