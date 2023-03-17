package cn.edu.nciae.ustudy.config.security.properties;

/**
 * @author tang
 * @date 2020/9/8 09:44
 */
public class JwtProperties {

    private String base64Secret;

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public Integer getExpireInSeconds() {
        return expireInSeconds;
    }

    public void setExpireInSeconds(Integer expireInSeconds) {
        this.expireInSeconds = expireInSeconds;
    }

    private Integer expireInSeconds;
}
