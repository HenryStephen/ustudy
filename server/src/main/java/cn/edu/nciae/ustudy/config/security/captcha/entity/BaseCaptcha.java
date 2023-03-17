package cn.edu.nciae.ustudy.config.security.captcha.entity;

import cn.edu.nciae.ustudy.utils.CaptchaUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * a foundation class for captcha
 *
 * @author tang
 */
public abstract class BaseCaptcha implements Serializable {

    public final String id;

    public final String code;

    public final LocalDateTime expireTime;

    public BaseCaptcha(Builder<?> builder) {
        this.id = builder.id;
        this.code = CaptchaUtils.generateVerifyCode(builder.length);
        this.expireTime = LocalDateTime.now().plusSeconds(builder.expireIn);
    }

    public static abstract class Builder<T extends Builder<T>> {
        private final String id;
        private final int length;
        private final int expireIn;

        public Builder(String id, int length, int expireIn) {
            this.id = id;
            this.length = length;
            this.expireIn = expireIn;
        }

        public abstract BaseCaptcha build();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
