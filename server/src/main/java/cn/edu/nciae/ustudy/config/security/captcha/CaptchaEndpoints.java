package cn.edu.nciae.ustudy.config.security.captcha;

import cn.edu.nciae.ustudy.config.Result;
import cn.edu.nciae.ustudy.config.security.captcha.entity.ImageCaptcha;
import cn.edu.nciae.ustudy.config.security.captcha.entity.SmsCaptcha;
import cn.edu.nciae.ustudy.config.security.properties.SecurityProperties;
import cn.edu.nciae.ustudy.utils.CaptchaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/code")
public class CaptchaEndpoints {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaEndpoints.class);

    @Autowired
    private SecurityProperties properties;

    @Autowired
    private CaptchaRepository repository;

    @GetMapping("/image")
    public void createImageCode(
            HttpServletResponse response,
            @RequestParam String deviceId,
            @RequestParam(defaultValue = "400") Integer width,
            @RequestParam(defaultValue = "100") Integer height,
            @RequestParam(defaultValue = "base64") String encoding
    ) throws IOException {
        int length = properties.getCaptcha().getImage().getLength();
        int expireIn = properties.getCaptcha().getImage().getExpireIn();
        ImageCaptcha captcha = new ImageCaptcha.Builder(
                deviceId, length, expireIn, width, height
        ).build();
        repository.save(deviceId, captcha, captcha.getClass(), expireIn);

        final String formatType = "JPEG";
        switch (encoding) {
            case "none":
                response.setContentType("image/jpeg");
                ImageIO.write(captcha.generateImage(), formatType ,response.getOutputStream());
                break;
            case "base64":
                response.getWriter().print(CaptchaUtils.toBase64(captcha.generateImage(), formatType));
                break;
            default:
                break;
        }
    }

    @GetMapping("/sms")
    public Result<String> createSmsCode(@RequestParam String deviceId) {
        int length = properties.getCaptcha().getSms().getLength();
        int expireIn = properties.getCaptcha().getSms().getExpireIn();
        SmsCaptcha captcha = new SmsCaptcha.Builder(deviceId, length, expireIn).build();
        repository.save(deviceId, captcha, captcha.getClass(), expireIn);
        // TODO Send sms code.
        logger.info("Sms captcha: {}", captcha.code);
        return Result.success("Success.");
    }
}
