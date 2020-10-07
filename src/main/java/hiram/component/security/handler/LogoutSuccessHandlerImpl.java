package hiram.component.security.handler;

import hiram.component.common.pojo.vo.LoginUser;
import hiram.component.common.service.ITokenService;
import hiram.common.utils.JsonUtils;
import hiram.common.enums.ResultCodeEnum;
import hiram.component.common.pojo.vo.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: HiramHe
 * @Date: 2020/5/19 12:07
 * @Description: ""
 */

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    ITokenService iTokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            LoginUser loginUser = iTokenService.getLoginUser(request);

            if(loginUser != null){
                // 删除用户缓存
                iTokenService.deleteLoginUser(loginUser.getUuid());
            }

            ResultObject resultObject = ResultObject.success(ResultCodeEnum.SUCCESS_LOGOUT,null);
            JsonUtils.writeObject(request,response,resultObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
