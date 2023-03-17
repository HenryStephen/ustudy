package cn.edu.nciae.ustudy.advice;

import cn.edu.nciae.ustudy.annotation.IgnoreResponseAdvice;
import cn.edu.nciae.ustudy.config.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Response encapsulation.
 * Make all controllers return {@code Result}.
 * If some methods or classes don't need, mark it with {@code IgnoreResponseWrapper}.
 *
 * @author tang
 * @date 2020/9/7 20:45
 */
@ControllerAdvice(basePackages = "cn.edu.nciae.ustudy.controller")
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if (methodParameter.getMethod() != null
                && methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter methodParameter,
            MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> aClass,
            ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse
    ) {
        String returnType = methodParameter.getParameterType().getName();
        if (StringUtils.equals(returnType, Result.class.getName())) {
            return body;
        }
        return Result.success(body);
    }
}
