package cn.edu.nciae.ustudy.config.security.captcha;

import cn.edu.nciae.ustudy.config.security.SecurityConstants;
import cn.edu.nciae.ustudy.config.security.captcha.entity.BaseCaptcha;
import cn.edu.nciae.ustudy.config.security.captcha.entity.ImageCaptcha;
import cn.edu.nciae.ustudy.config.security.captcha.entity.SmsCaptcha;
import cn.edu.nciae.ustudy.config.security.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * use to validate captcha
 *
 * @author tang
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    private SecurityProperties securityProperties;

    private CaptchaRepository captchaRepository;

    private AuthenticationFailureHandler authenticationFailureHandler;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    public CaptchaFilter(AuthenticationFailureHandler handler, CaptchaRepository repository, SecurityProperties properties) {
        this.authenticationFailureHandler = handler;
        this.captchaRepository = repository;
        this.securityProperties = properties;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest httpServletRequest,
            @NonNull HttpServletResponse httpServletResponse,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            filter(httpServletRequest);
        } catch (CaptchaValidateException e) {
            authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void filter(HttpServletRequest request) throws ServletRequestBindingException, CaptchaValidateException {
        if (contain(request, securityProperties.getCaptcha().getImage().getUrls())) {
            validate(new ServletWebRequest(request), ImageCaptcha.class);
        }
        if (contain(request, securityProperties.getCaptcha().getSms().getUrls())) {
            validate(new ServletWebRequest(request), SmsCaptcha.class);
        }
    }

    private void validate(ServletWebRequest servletWebRequest, Class<?> type) {
        String deviceId = servletWebRequest.getHeader(SecurityConstants.PARAM_DEVICE_ID);
        String codeInRequest = servletWebRequest.getHeader(SecurityConstants.PARAM_CAPTCHA);
        BaseCaptcha codeInStorage = captchaRepository.get(deviceId, type);

        if (StringUtils.isBlank(codeInRequest)) {
            throw new CaptchaValidateException("验证码为空");
        }

        if (codeInStorage == null) {
            throw new CaptchaValidateException("验证码过期");
        }

        if (codeInStorage.isExpired()) {
            throw new CaptchaValidateException("验证码过期");
        }

        if (!StringUtils.equalsIgnoreCase(codeInRequest, codeInStorage.code)) {
            throw new CaptchaValidateException("验证码不匹配");
        }

        captchaRepository.remove(deviceId, type);
    }

    private boolean contain(HttpServletRequest request, List<String> urls) {
        for (String url : urls) {
            if (StringUtils.contains(url, " ")) {
                String[] methods = StringUtils.substringBefore(url, " ").split(",");
                String urlWithoutMethod = StringUtils.substringAfter(url, " ");
                if (StringUtils.equalsAnyIgnoreCase(request.getMethod(), methods)
                        && antPathMatcher.match(urlWithoutMethod, request.getRequestURI())) {
                    return true;
                }
            }
            else if (antPathMatcher.match(url, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    public void setCaptchaRepository(CaptchaRepository captchaRepository) {
        this.captchaRepository = captchaRepository;
    }
}
