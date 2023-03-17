package cn.edu.nciae.ustudy.advice;

import cn.edu.nciae.ustudy.config.Result;
import cn.edu.nciae.ustudy.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YeLi
 * @date 2020/9/7 21:16
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ServiceException exception = (ServiceException) e;
        logger.error("Service Exception:", e);
        return Result.fail(exception.getStatus(), exception.getClass().getSimpleName(), exception.getMessage());
    }

}
