package hiram.module.system.controller;

import com.baomidou.mybatisplus.extension.api.R;
import hiram.common.web.domain.dto.LoginUser;
import hiram.common.service.ITokenService;
import hiram.common.utils.ServletUtils;
import hiram.common.web.ResultCode;
import hiram.common.web.ResultObject;
import hiram.module.system.domain.entity.SysRole;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/19 11:57
 * @Description: ""
 */

@RestController
@Api(tags = "登录相关接口")
public class LoginController {

    @Autowired
    ITokenService myTokenService;

    @Autowired
    IRoleService roleService;

    /*
    关闭spring security后起作用。
     */
    @PostMapping("/login")
    public ResultObject<?> login(){
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjQxMjgwNDIwLThkOWUtNDRiNC04ZTJmLTQ0YTUyZDY2MTJjNyJ9.EP50erogGwiirfYczoo4LW1daKnPIH1g013HXGVBHu4fJD7wKXIyhzepqFhM5eAfhkJtZw6cS17RAsDRyvyA8Q";
        Map data = new HashMap();
        data.put("token",token);
        ResultObject resultObject = ResultObject.success(ResultCode.SUCCESS_AUTHENTICATE,data);

        return resultObject;
    }

    @GetMapping("/getInfo")
    @ApiOperation(value = "获取当前登录用户的详细信息")
    public ResultObject<?> profile() throws Exception {

        /*
        如果有token，则已经在request中携带了
         */
        LoginUser loginUser = myTokenService.getLoginUser(ServletUtils.getRequest());

        ResultObject<Map<String,Object>> resultObject;

        if(loginUser != null){
            SysUser sysUser = loginUser.getUser();
            List<SysRole> roles = roleService.selectRolesByUsername(sysUser.getUsername());
            List<String> roleNames = new ArrayList<>();
            for (SysRole role:roles) {
                roleNames.add(role.getRoleNameEn());
            }
            Map<String,Object> data = new HashMap<>();
            data.put("user",sysUser);
            data.put("roleNames",roleNames);

            resultObject = ResultObject.success(ResultCode.SUCCESS,data);
        } else {
            resultObject = ResultObject.failed(ResultCode.LOGIN_EXPIRED);
        }

        return resultObject;
    }
}
