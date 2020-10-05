package hiram.module.system.service;

import hiram.module.system.pojo.query.UserRoleInsertServiceQuery;
import hiram.module.system.pojo.po.UserRole;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/10/2 16:17
 * @Description: ""
 */

public interface UserRoleService {

    /**
     * 根据userId、roleId删除用户角色
     * */
    Long deleteUserRoleByUserIdRoleId(Long userId,Long roleId) throws Exception;

    /**
     * 根据userId删除用户角色
     * */
    Long deleteUserRoleByUserId(Long userId) throws Exception;

    /**
     * 插入用户角色
     * @param userRoleInsertServiceQuery
     * @return
     */
    UserRole insertUserRole(UserRoleInsertServiceQuery userRoleInsertServiceQuery) throws Exception;

    /**
     * 批量插入用户角色
     * @param userRoleList
     * @return
     */
    Long batchInsertUserRole(List<UserRole> userRoleList);

}
