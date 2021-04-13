package com.ht.readingisgood.securitylibrary.interceptor;

import com.ht.readingisgood.securitylibrary.model.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private SecurityContext securityContext;

    @Autowired
    public RestTemplateInterceptor(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public ClientHttpResponse intercept(
            HttpRequest httpRequest, byte[] body,
            ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, securityContext.getToken());
        return clientHttpRequestExecution.execute(httpRequest, body);
    }
}