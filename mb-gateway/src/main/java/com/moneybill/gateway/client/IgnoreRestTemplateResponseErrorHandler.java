package com.moneybill.gateway.client;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class IgnoreRestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(@NonNull ClientHttpResponse response) {
        return false;
    }

    @Override
    public void handleError(@NonNull ClientHttpResponse response) {
    }
}
