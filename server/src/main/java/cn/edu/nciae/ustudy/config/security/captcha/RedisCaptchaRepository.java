package cn.edu.nciae.ustudy.config.security.captcha;

import cn.edu.nciae.ustudy.config.security.captcha.entity.BaseCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCaptchaRepository implements CaptchaRepository {

    private static final String REDIS_PREFIX_CAPTCHA = "captcha";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(String deviceId, BaseCaptcha captcha, Class<?> type, Integer expireIn) {
        redisTemplate.opsForValue().set(buildKey(deviceId, type), captcha, expireIn, TimeUnit.SECONDS);
    }

    @Override
    public BaseCaptcha get(String deviceId, Class<?> type) {
        Object value = redisTemplate.opsForValue().get(buildKey(deviceId, type));
        return (BaseCaptcha) Optional.ofNullable(value).orElse(null);
    }

    @Override
    public void remove(String deviceId, Class<?> type) {
        redisTemplate.delete(buildKey(deviceId, type));
    }

    private String buildKey(String deviceId, Class<?> type) {
        return StringUtils.joinWith(":",
                REDIS_PREFIX_CAPTCHA, type.getName().toLowerCase(), deviceId);
    }
}
