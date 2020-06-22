package hiram.module.system.controller;

import hiram.common.web.ResultObject;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 20:20
 * @Description: ""
 */

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/list")
    public ResultObject selectUserList(){
        List<SysUser> sysUsers = iUserService.selectUserList();
        sysUsers.forEach(System.out::println);
        return null;
    }
}
