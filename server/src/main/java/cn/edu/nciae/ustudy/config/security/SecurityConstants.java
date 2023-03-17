package cn.edu.nciae.ustudy.config.security;

/**
 * @author tang
 * @date 2020/9/8 09:28
 */
public class SecurityConstants {

    /**
     * URL 需要登录
     */
    public static final String URL_REQUIRE_LOGIN = "/token/require";

    /**
     * URL 获取token
     */
    public static final String URL_TOKEN = "/token";

    /**
     * URL 图形验证码
     */
    public static final String URL_IMAGE_CODE = "/code/image";

    /**
     * URL 短信验证码
     */
    public static final String URL_SMS_CODE = "/code/sms";

    /**
     * 参数名 设备id
     */
    public static final String PARAM_DEVICE_ID = "deviceId";

    /**
     * 参数名 验证码
     */
    public static final String PARAM_CAPTCHA = "captcha";


    public static final String PARAM_USERNAME = "phone";


    public static final String PARAM_PASSWORD = "password";
}
