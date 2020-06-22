package hiram.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.module.system.mapper.UserMapper;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 11:19
 * @Description: ""
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public void insert(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));

        userMapper.insert(sysUser);
    }

    @Override
    public SysUser selectUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        SysUser sysUser = userMapper.selectOne(queryWrapper);

        return sysUser;
    }

    @Override
    public List<SysUser> selectUserList() {
        List<SysUser> sysUsers = userMapper.selectUserList();

        return sysUsers;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        return false;
    }


    /**
     * 认证业务
     * 实现UserDetailsService中的loadUserByUsername方法
     * @param username 用户在浏览器输入的用户名.
     * @return UserDetails是spring security自己的用户对象
     * @throws UsernameNotFoundException
     */

}
