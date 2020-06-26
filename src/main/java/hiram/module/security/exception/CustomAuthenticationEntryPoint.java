package hiram.module.security.exception;

import hiram.common.pojo.ResultObject;
import hiram.common.utils.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: HiramHe
 * @Date: 2020/4/29 20:57
 * @Description: ""
 */

/**
 * 统一处理 认证异常。
 * 用来处理匿名用户访问无权限资源时的异常（即未登录，或者登录状态过期失效）
 * authenticationEntryPoint其实是在没有认证时，
 * 配置自动跳转到登录页面的url，这里将其改造成前后端分离接口。
 * 如果用户在未登录状态下直接请求需要登录的接口，应该报401，
 * 然而spring security默认会报403。
 * 因为spring security的未登录用户会拥有一个已认证的匿名权限，
 * 此时会认为其没有权限所以报403。
 * 可重新配置成401.
 *
 */

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if(logger.isDebugEnabled()){
            logger.debug("AuthenticationException:" + authException.getClass());
        }

        String msg = "";
        if( authException instanceof CustomAuthenticationException){
            msg = authException.getMessage();
        } else if(authException instanceof InsufficientAuthenticationException) {
            msg = "请登录!";
        }

        ResultObject<String> resultObject = ResultObject.failed(HttpServletResponse.SC_UNAUTHORIZED, 0, msg,null);

        JsonUtils.writeObject(request,response,resultObject);
    }
}
