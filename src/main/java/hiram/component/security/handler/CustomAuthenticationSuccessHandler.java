package hiram.component.security.handler;

import hiram.common.enums.ResultCode;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.common.service.ITokenService;
import hiram.component.common.pojo.vo.LoginUser;
import hiram.common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/4/29 16:21
 * @Description: ""
 */

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ITokenService myTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        /*
        done
        生成token
         */
        String token = "";
        /*
        createToken里面已经将token和loginUser放到redis中去了
         */
        token = myTokenService.createToken(loginUser);

        /*
         * 封装返回前端对象
         * R：mybatis-plus提供的结果封装工具
         */
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);

        ResultObject< Map<String,Object> > resultObject =
                ResultObject.success(
                        HttpServletResponse.SC_OK,
                        ResultCode.SUCCESS_AUTHENTICATE,
                        data
                );


        //写回浏览器
        JsonUtils.writeObject(request,response,resultObject);

    }


}
