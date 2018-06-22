package org.ld.bt2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.ld.bt2.util.resultJson.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Slf4j
@RestControllerAdvice
public class ExceptionUtil {

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseResult<String> parseException(HttpServletRequest request,
                                                 Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage(exception.getMessage());
        return result;
    }
}
