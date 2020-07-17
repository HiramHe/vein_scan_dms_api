package hiram.module.system.controller;

import hiram.common.service.ITokenService;
import hiram.common.utils.ServletUtils;
import hiram.common.enums.ResultCode;
import hiram.common.web.domain.entity.ResultObject;
import hiram.common.web.domain.dto.LoginUser;
import hiram.module.system.domain.entity.SysRole;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 21:32
 * @Description: ""
 */

@RestController
@RequestMapping("/profile")
@Api(tags = "个人中心接口")
public class ProfileController {

    @Autowired
    ITokenService iTokenService;

    @Autowired
    IRoleService roleService;

    @GetMapping("/getInfo")
    @ApiOperation(value = "获取当前登录用户详细信息")
    public ResultObject<?> profile() throws Exception {

        /*
        如果有token，则已经在request中携带了
         */
        LoginUser loginUser = iTokenService.getLoginUser(ServletUtils.getRequest());

        ResultObject<Map<String,Object>> resultObject;

        if(loginUser != null){
            SysUser sysUser = loginUser.getUser();
            List<SysRole> roles = roleService.selectRolesByUsername(sysUser.getUsername());
            sysUser.setRoles(roles);

            Map<String,Object> data = new HashMap<>();
            data.put("user",sysUser);

            resultObject = ResultObject.success(ResultCode.SUCCESS,data);
        } else {
            resultObject = ResultObject.failed(ResultCode.LOGIN_EXPIRED);
        }

        return resultObject;
    }
}
