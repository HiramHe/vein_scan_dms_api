package hiram.component.exception;

import hiram.common.enums.ResultCode;
import hiram.component.common.pojo.vo.ResultObject;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: HiramHe
 * @Date: 2020/6/26 15:08
 * @Description: ""
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({JwtException.class})
    public ResultObject<?> handleException(JwtException exception){

        return ResultObject.failed(ResultCode.TOKEN_UNTRUSTED);
    }

    @ExceptionHandler({TokenException.class})
    public ResultObject<?> handleException(TokenException exception){

        return ResultObject.failed(ResultCode.TOKEN_NULL);
    }

    @ExceptionHandler({UserException.class})
    public ResultObject<?> handleException(UserException exception){

        return ResultObject.failed(ResultCode.USER_NOT_EXIST);
    }
}
