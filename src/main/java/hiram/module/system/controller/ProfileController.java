package hiram.module.system.controller;

import hiram.component.common.service.ITokenService;
import hiram.common.utils.ServletUtils;
import hiram.common.enums.ResultCode;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.common.pojo.vo.LoginUser;
import hiram.module.system.pojo.entity.SysRole;
import hiram.module.system.pojo.entity.SysUser;
import hiram.module.system.service.IRoleService;
import hiram.module.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    IUserService iUserService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @ApiOperation(value = "获取当前登录用户详细信息")
    @GetMapping("/getInfo")
    public ResultObject<?> profile() throws Exception {

        /*
        如果有token，则已经在request中携带了。
        抛出的异常会被全局异常处理器捕获并处理。
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

    @ApiOperation(value = "修改密码", hidden = false )
    @PostMapping(value = "/updatePassword")
    public ResultObject<?> updatePassword(String oldPassword, String newPassword) throws Exception {
        LoginUser loginUser = iTokenService.getLoginUser(ServletUtils.getRequest());

        Long userId = loginUser.getUser().getUserId();
        String password = loginUser.getPassword();

        if (!encoder.matches(oldPassword,password)) {
            return ResultObject.failed(ResultCode.OLDPASSWORD_ERROR);
        }
        if( encoder.matches(newPassword,password)){
            return ResultObject.failed(ResultCode.NEWPASSWORD_SAME_ERROR);
        }

        if( iUserService.resetUserPwd(userId,newPassword) > 0){
            //更新缓存用户密码
            loginUser.getUser().setPassword(encoder.encode(newPassword));
            iTokenService.setLoginUser(loginUser);

            return ResultObject.success(ResultCode.SUCCESS);
        }

        return ResultObject.failed(ResultCode.RESETPASSWORD_ERROR);
    }
}
