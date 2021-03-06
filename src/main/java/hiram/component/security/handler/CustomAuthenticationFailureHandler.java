package hiram.component.security.handler;


import hiram.common.enums.ResultCodeEnum;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.common.utils.JsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理
 */

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {

        ResultObject<String> resultObject = ResultObject.failed(HttpServletResponse.SC_OK, ResultCodeEnum.FAILED_AUTHENTICATE);

        //输出
        JsonUtils.writeObject(request, response, resultObject);
    }
}