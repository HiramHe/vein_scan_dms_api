package hiram.module.system.controller;

import hiram.common.enums.ResultCodeEnum;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.module.system.pojo.query.LoginViewQuery;
import hiram.module.system.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResultObject<?> login(@Valid LoginViewQuery loginViewQuery){

        if(logger.isDebugEnabled()){
            System.out.println("LoginBody:"+ loginViewQuery);
        }

        if(StringUtils.isEmpty(loginViewQuery.getUsername()) || StringUtils.isEmpty(loginViewQuery.getPassword())){
            return ResultObject.failed(ResultCodeEnum.USERNAME_PASSWORD_NULL);
        }

        String token = loginService.login(loginViewQuery.getUsername(), loginViewQuery.getPassword());

        if(token != null){
            Map<String,String> data = new HashMap<>();
            data.put("token",token);

            return ResultObject.success(ResultCodeEnum.SUCCESS_AUTHENTICATE,data);
        }else {
            return ResultObject.failed(ResultCodeEnum.FAILED_AUTHENTICATE);
        }

    }


}
