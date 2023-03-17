package cn.edu.nciae.ustudy.config.security;

import cn.edu.nciae.ustudy.config.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tang
 * @date 2020/9/7 16:12
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    public static final Logger logger = LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        Result<String> res = Result.fail(403, exception.getClass().getName(), exception.getMessage());
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}
