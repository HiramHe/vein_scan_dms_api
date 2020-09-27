package hiram.component.security.service.impl;

import hiram.component.common.pojo.vo.LoginUser;
import hiram.module.system.pojo.entity.SysRole;
import hiram.module.system.pojo.entity.SysUser;
import hiram.module.system.service.IRoleService;
import hiram.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 14:30
 * @Description: ""
 */

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
         done
         1.根据用户名查询数据库
        */
        SysUser sysUser = userService.selectUserByUsername(username);

        if(sysUser==null){
            /*
             * UsernameNotFoundException是AuthenticationException接口的实现，
             * 凡是AuthenticationException异常，去AuthenticationEntryPoint
             * 中进行统一处理。
            */
            throw new UsernameNotFoundException(String.format("用户[%s]不存在!",username));
        }

        /*
         *
         * "{noop}"+sysUser.getPassword()
         * {noop}后面的密码，spring security会认为是明文
         * done 加密
        */

        return createLoginUser(sysUser);
    }

    public UserDetails createLoginUser(SysUser sysUser) {

        /*
         * Collection<? extends GrantedAuthority> authorities;
         * ?：表示只要是GrantedAuthority类型的对象就行
         */

        /*
         done
         根据用户名获取用户的角色
        */
        List<SysRole> sysRoles = roleService.selectRolesByUsername(sysUser.getUsername());

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(sysUser);
        loginUser.setSysRoles(sysRoles);

        return loginUser;
    }
}
