package cn.edu.nciae.ustudy.config.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * All custom properties
 *
 * @author tang
 */
@ConfigurationProperties(prefix = "ustudy.security")
public class SecurityProperties {

    private CaptchaProperties captcha = new CaptchaProperties();

    private RsaProperties rsa = new RsaProperties();

    private JwtProperties jwt = new JwtProperties();

    public CaptchaProperties getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaProperties captcha) {
        this.captcha = captcha;
    }

    public RsaProperties getRsa() {
        return rsa;
    }

    public void setRsa(RsaProperties rsa) {
        this.rsa = rsa;
    }

    public JwtProperties getJwt() {
        return jwt;
    }

    public void setJwt(JwtProperties jwt) {
        this.jwt = jwt;
    }
}
