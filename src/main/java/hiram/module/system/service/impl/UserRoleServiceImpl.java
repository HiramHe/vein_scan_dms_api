package hiram.module.system.service.impl;

import hiram.module.system.mapper.UserRoleMapper;
import hiram.module.system.pojo.query.UserRoleInsertServiceQuery;
import hiram.module.system.pojo.po.UserRole;
import hiram.module.system.service.UserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/10/2 16:19
 * @Description: ""
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 根据userId、roleId删除用户角色
     *
     * @param userId
     * @param roleId
     */
    @Override
    public Long deleteUserRoleByUserIdRoleId(Long userId, Long roleId) throws Exception {

        Long rt = userRoleMapper.deleteUserRoleByUserIdRoleId(userId, roleId);

        return rt;
    }

    /**
     * 根据userId删除用户角色
     *
     * @param userId
     */
    @Override
    public Long deleteUserRoleByUserId(Long userId) throws Exception {
        return userRoleMapper.deleteUserRoleByUserId(userId);
    }

    /**
     * 插入用户角色
     *
     * @param userRoleInsertServiceQuery
     * @return
     */
    @Override
    public UserRole insertUserRole(UserRoleInsertServiceQuery userRoleInsertServiceQuery) throws Exception {

        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRoleInsertServiceQuery,userRole);

        Long rt = userRoleMapper.insertUserRole(userRole);

        return userRole;
    }

    /**
     * 批量插入用户角色
     *
     * @param userRoleList
     * @return
     */
    @Override
    public Long batchInsertUserRole(List<UserRole> userRoleList) {

        return userRoleMapper.batchInsertUserRole(userRoleList);
    }
}
