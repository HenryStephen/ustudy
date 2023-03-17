package cn.edu.nciae.ustudy.config.security.captcha.entity;

import cn.edu.nciae.ustudy.utils.CaptchaUtils;

import java.awt.image.BufferedImage;


public class ImageCaptcha extends BaseCaptcha {

    private final int width;

    private final int height;

    private ImageCaptcha(Builder builder) {
        super(builder);
        this.height = builder.height;
        this.width = builder.width;
    }

    public static class Builder extends BaseCaptcha.Builder<Builder> {
        private final int width;
        private final int height;

        public Builder(String id, int length, int expireIn, int width, int height) {
            super(id, length, expireIn);
            this.width = width;
            this.height = height;
        }

        @Override
        public ImageCaptcha build() {
            return new ImageCaptcha(this);
        }
    }

    public BufferedImage generateImage() {
        return CaptchaUtils.generateImage(width, height, code);
    }
}
