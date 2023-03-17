package cn.edu.nciae.ustudy.config.security.properties;

import java.util.ArrayList;

public class ImageCaptchaProperties {

    private int width = 400;

    private int height = 100;

    private int length = 4;

    private int expireIn = 60;

    private ArrayList<String> urls = new ArrayList<>();

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
