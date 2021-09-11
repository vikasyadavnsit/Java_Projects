package com.spring.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Component
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!List.of(RequestMethod.GET.name(), RequestMethod.POST.name(), RequestMethod.PUT.name(), RequestMethod.DELETE.name(),
                RequestMethod.PATCH.name()).contains(request.getMethod())) {
            log.error("Disallowed Http Request Method : {}", request.getMethod());
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
            return false;
        }
        return true;
    }

}
