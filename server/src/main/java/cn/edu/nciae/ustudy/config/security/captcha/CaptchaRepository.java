package cn.edu.nciae.ustudy.config.security.captcha;


import cn.edu.nciae.ustudy.config.security.captcha.entity.BaseCaptcha;

public interface CaptchaRepository {

    void save(String deviceId, BaseCaptcha captcha, Class<?> type, Integer expireIn);

    BaseCaptcha get(String deviceId, Class<?> type) throws CaptchaValidateException;

    void remove(String deviceId, Class<?> type) throws CaptchaValidateException;
}
