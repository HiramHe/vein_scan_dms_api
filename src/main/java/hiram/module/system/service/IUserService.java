package hiram.module.system.service;

import hiram.module.system.domain.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:59
 * @Description: ""
 */

public interface IUserService {

    void insert(SysUser sysUser);

    SysUser selectUserByUsername(String username);

    List<SysUser> selectUserList();
}
