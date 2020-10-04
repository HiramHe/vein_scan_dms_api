package hiram.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.common.constant.Constants;
import hiram.module.system.mapper.UserMapper;
import hiram.module.system.mapper.UserRoleMapper;
import hiram.module.system.pojo.dto.UserInsertArgsDTO;
import hiram.module.system.pojo.dto.UserQueryArgsDTO;
import hiram.module.system.pojo.dto.UserQueryRtDTO;
import hiram.module.system.pojo.dto.UserUpdateArgsDTO;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.po.UserRole;
import hiram.module.system.pojo.vo.UserInsertArgsVO;
import hiram.module.system.pojo.vo.UserQueryArgsVO;
import hiram.module.system.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private UserRoleMapper userRoleMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    /*
    查询
    */

    @Override
    public SysUser selectUserByUsername(String username) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);

        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public List<UserQueryRtDTO> selectUserList(UserQueryArgsVO userQueryArgsVO) {

        //将vo转化为dto
        UserQueryArgsDTO userQueryArgsDTO = new UserQueryArgsDTO();
        if (userQueryArgsVO != null){
            BeanUtils.copyProperties(userQueryArgsVO,userQueryArgsDTO);
        }

        List<UserQueryRtDTO> userQueryRtDTOs = userMapper.selectUserList(userQueryArgsDTO);

        return userQueryRtDTOs;
    }

    /*
    恢复
    */

    @Override
    public Long recoverDeletedUserById(Long userId) {
        return userMapper.recoverDeletedUserById(userId);
    }

    @Override
    public Long recoverDeletedUserByIds(Long[] userIds) {
        return userMapper.recoverDeletedUserByIds(userIds);
    }

    /*
    更新
    */

    @Override
    @Transactional
    public Long updateUser(UserUpdateArgsDTO userUpdateArgsDTO) throws Exception {

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userUpdateArgsDTO,sysUser);

        //更新用户
        Long rt = userMapper.updateUser(sysUser);

        if (rt == 0){
            return rt;
        }

        List<UserRole> userRoleList = userUpdateArgsDTO.getUserRoleList();
        if (userRoleList!=null && userRoleList.size()>0){
            //删除用户与角色的关联
            userRoleMapper.deleteUserRoleByUserId(sysUser.getUserId());
            //新增用户与角色关联
            userRoleMapper.batchInsertUserRole(userRoleList);
        }

        return rt;
    }

    /*
    删除
    */

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

    /*
    插入
    */

    @Override
    public SysUser insertUser(UserInsertArgsVO userInsertArgsVO) {

        //封装vo到dto中，不修改vo中的值
        UserInsertArgsDTO userInsertArgsDTO = new UserInsertArgsDTO();
        BeanUtils.copyProperties(userInsertArgsVO,userInsertArgsDTO);

        userInsertArgsDTO.setPassword(passwordEncoder.encode(userInsertArgsDTO.getPassword()));

        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userInsertArgsDTO,sysUser);

        Long rt = userMapper.insertUser(sysUser);

        return sysUser;
    }

    /*
    校验
    */

    @Override
    public boolean checkUserNameUnique(Long userId, String username) {
        userId = userId==null ? -1:userId;

        SysUser db_user = userMapper.checkUserNameUnique(username);

        if (db_user!=null && !db_user.getUserId().equals(userId)){
            return Constants.NOT_UNIQUE;
        }

        return Constants.UNIQUE;
    }

    @Override
    public boolean checkPhoneUnique(Long userId,String phoneNumber) {

        userId = userId==null ? -1:userId;

        SysUser db_user = userMapper.checkPhoneUnique(phoneNumber);

        if (db_user!=null && !db_user.getUserId().equals(userId)){
            return Constants.NOT_UNIQUE;
        }

        return Constants.UNIQUE;
    }

    @Override
    public boolean checkEmailUnique(Long userId,String email) {

        userId = userId==null ? -1:userId;

        SysUser db_user = userMapper.checkEmailUnique(email);

        if (db_user!=null && !db_user.getUserId().equals(userId)){
            return Constants.NOT_UNIQUE;
        }

        return Constants.UNIQUE;
    }

    /*
    重置密码
    */

    @Override
    public int resetUserPwd(Long userId, String newPassword) {

        String newPasswordEncoded = passwordEncoder.encode(newPassword);

        return userMapper.resetUserPwd(userId,newPasswordEncoded);
    }
}
