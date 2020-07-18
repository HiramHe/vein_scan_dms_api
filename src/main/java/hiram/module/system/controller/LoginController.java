package hiram.module.system.controller;

import hiram.common.enums.ResultCode;
import hiram.common.web.domain.entity.ResultObject;
import hiram.module.system.domain.entity.LoginBody;
import hiram.module.system.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/19 11:57
 * @Description: ""
 */

@RestController
@Api(tags = "登录接口")
public class LoginController {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    LoginService loginService;

    /*
    关闭spring security后起作用。
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录处理")
    public ResultObject<?> login(@RequestBody LoginBody loginBody){

        if(logger.isDebugEnabled()){
            System.out.println("LoginBody:"+loginBody);
        }

        if(StringUtils.isEmpty(loginBody.getUsername()) || StringUtils.isEmpty(loginBody.getPassword())){
            return ResultObject.failed(ResultCode.USERNAME_PASSWORD_NULL);
        }

        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());

        if(token != null){
            Map<String,String> data = new HashMap<>();
            data.put("token",token);

            return ResultObject.success(ResultCode.SUCCESS_AUTHENTICATE,data);
        }else {
            return ResultObject.failed(ResultCode.FAILED_AUTHENTICATE);
        }

    }


}
