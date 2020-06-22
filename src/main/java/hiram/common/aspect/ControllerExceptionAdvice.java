package hiram.common.aspect;

/**
 * @Author: HiramHe
 * @Date: 2020/5/5 12:10
 * @Description: ""
 */

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * Spring 提供。
 * 这是处理控制层异常的切面类。
 *
 * spring security里面异常是filter层次，还没有走到controller层，
 * 所以这里不能处理filter的异常。
 */

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler({AccessDeniedException.class})
    public void handleException(){
        System.out.println("woshi 403");
    }
}
