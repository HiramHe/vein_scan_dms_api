package hiram.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.common.utils.MyStringUtils;
import hiram.module.system.domain.vo.UserInsertVO;
import hiram.module.system.domain.vo.UserListParam;
import hiram.module.system.domain.vo.UserUpdateParam;
import hiram.module.system.mapper.UserMapper;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<SysUser> selectUserList(UserListParam userListParam) {

        return userMapper.selectUserList(userListParam);
    }

    @Override
    public Long recoverDeletedUserById(Long userId) {
        return userMapper.recoverDeletedUserById(userId);
    }

    @Override
    public Long updateUser(UserUpdateParam userUpdateParam) {
        return userMapper.updateUser(userUpdateParam);
    }

    @Override
    public Long logicallyDeleteUserByIds(Long[] userIds) {
        return userMapper.logicallyDeleteUserByIds(userIds);
    }

    @Override
    public Long logicallyDeleteUserById(Long userId) {
        return userMapper.logicallyDeleteUserById(userId);
    }

    @Override
    public Long physicallyDeleteUserById(Long userId) {
        return userMapper.physicallyDeleteUserById(userId);
    }

    @Override
    public Long recoverDeletedUserByIds(Long[] userIds) {
        return userMapper.recoverDeletedUserByIds(userIds);
    }

    @Override
    public Long insertUser(UserInsertVO userInsertVO) {
        userInsertVO.setPassword(passwordEncoder.encode(userInsertVO.getPassword()));
        return userMapper.insertUser(userInsertVO);
    }

    @Override
    public boolean checkUserNameExist(String username) {

        Long count = userMapper.checkUserNameExist(username);

        return count > 0;
    }

    @Override
    public boolean checkPhoneUnique(SysUser user) {

        Long userId = MyStringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser db_user = userMapper.checkPhoneUnique(user.getPhoneNumber());

        return db_user == null || db_user.getUserId().equals(userId);
    }

    @Override
    public boolean checkEmailUnique(SysUser user) {

        Long userId = MyStringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser db_user = userMapper.checkEmailUnique(user.getEmail());

        return db_user == null || db_user.getUserId().equals(userId);
    }
}
