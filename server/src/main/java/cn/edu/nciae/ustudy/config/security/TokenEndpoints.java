package cn.edu.nciae.ustudy.config.security;

import cn.edu.nciae.ustudy.config.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tang
 * @date 2020/9/7 20:41
 */
@RestController
@RequestMapping("/token")
public class TokenEndpoints {

    @GetMapping("/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> require() {
        return Result.fail(401, "unauthorized", "Need to be authorized.");
    }
}
