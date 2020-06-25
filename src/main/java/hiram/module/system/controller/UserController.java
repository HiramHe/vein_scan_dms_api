package hiram.module.system.controller;

import hiram.common.web.ResultCode;
import hiram.common.web.ResultObject;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 20:20
 * @Description: ""
 */

@Api( tags = "system-User-Controller")
@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/list")
    public ResultObject<Map<String,Object>> selectUserList(){

        Map<String,Object> data = new HashMap<>();

        List<SysUser> sysUsers = iUserService.selectUserList();
        data.put("users",sysUsers);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }
}
