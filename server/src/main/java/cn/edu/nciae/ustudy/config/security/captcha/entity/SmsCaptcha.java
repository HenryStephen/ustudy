package cn.edu.nciae.ustudy.config.security.captcha.entity;

/**
 * @author tang
 * @date 2020/9/8 18:59
 */
public class SmsCaptcha extends BaseCaptcha {

    public SmsCaptcha(Builder builder) {
        super(builder);
    }

    public static class Builder extends BaseCaptcha.Builder<Builder> {

        public Builder(String id, int length, int expireIn) {
            super(id, length, expireIn);
        }

        @Override
        public SmsCaptcha build() {
            return new SmsCaptcha(this);
        }
    }
}
