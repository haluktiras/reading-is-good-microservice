package com.ht.readingisgood.securitylibrary.interceptor;

import com.ht.readingisgood.securitylibrary.model.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.ht.readingisgood.securitylibrary.service.constants.SecurityServiceConstants.AUTHORIZATION;

@Component
public class PreRequestInterceptor implements HandlerInterceptor {

    private SecurityContext securityContext;

    @Autowired
    public PreRequestInterceptor(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        String requestToken = request.getHeader(AUTHORIZATION);
        securityContext.setToken(Optional.ofNullable(requestToken).orElse(null));
        return true;
    }
}