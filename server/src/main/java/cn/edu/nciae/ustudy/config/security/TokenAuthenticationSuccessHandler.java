package cn.edu.nciae.ustudy.config.security;

import cn.edu.nciae.ustudy.config.Result;
import cn.edu.nciae.ustudy.config.security.jwt.TokenProvider;
import cn.edu.nciae.ustudy.po.User;
import cn.edu.nciae.ustudy.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import static cn.edu.nciae.ustudy.config.security.SecurityConstants.PARAM_USERNAME;

/**
 * Generate and return jwt when a user log in successfully.
 *
 * @author tang
 * @date 2020/9/7 16:38
 */
@Component
public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationSuccessHandler.class);

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String phone = ServletRequestUtils.getStringParameter(request, PARAM_USERNAME);
        User user = userService.getByPhone(phone);
        String token = tokenProvider.create(authentication);

        HashMap<String, Object> data = new HashMap<>();
        data.put("user_id", user.getId());
        data.put("phone", user.getTelephone());
        data.put("username", user.getUsername());
        data.put("token", token);

        response.setContentType("application/json;charset:UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Result.success(data)));
        logger.info("Log in success, user: {}", phone);
    }
}
