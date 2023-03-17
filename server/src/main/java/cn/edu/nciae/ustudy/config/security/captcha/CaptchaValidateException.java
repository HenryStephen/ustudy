package cn.edu.nciae.ustudy.config.security.captcha;


import org.springframework.security.core.AuthenticationException;

class CaptchaValidateException extends AuthenticationException {
    CaptchaValidateException(String msg) {
        super(msg);
    }
}
