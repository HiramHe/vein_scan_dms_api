package hiram.module.security.handler;

import hiram.common.enums.ResultCode;
import hiram.common.pojo.ResultObject;
import hiram.common.utils.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: HiramHe
 * @Date: 2020/4/29 21:00
 * @Description: ""
 */

/**
 * 统一处理 权限异常。
 * 解决认证过的用户访问无权限资源时的异常.
 * 已登录成功，但是请求的url超出其权限.
 * spring security官方提供的，只能处理403异常,
 * 不推荐，太单一了。
 * AccessDeniedHandler的默认实现AccessDeniedHandler中只有对页面请求的处理，
 * 没有对Ajax的处理。
 */

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String msg = "";
        msg = accessDeniedException.getMessage();
        msg = "权限不足";

        ResultObject<String> resultObject = ResultObject.failed(200, ResultCode.FAILED.getCode(), msg,null);

        JsonUtils.writeObject(request,response,resultObject);
    }
}
