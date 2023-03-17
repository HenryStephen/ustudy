package cn.edu.nciae.ustudy.config.security.properties;

import java.util.ArrayList;

public class SmsCaptchaProperties {

    private int length = 4;

    private int expireIn = 60;

    private ArrayList<String> urls = new ArrayList<>();

    public ArrayList<String> getUrls() {
        return urls;
    }

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
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
