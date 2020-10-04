package hiram.module.system.mapper;

import hiram.module.system.pojo.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/10/2 16:23
 * @Description: ""
 */

/*
注意，在spring中，java.mysql.SQLException及其子类 都被映射为
org.springframework.dao.DataAccessException及其子类，因此捕获异常时，我们需要捕获DataAccessException相关的异常.

例如：出现 java.sql.SQLIntegrityConstraintViolationException，要捕获时，我们应该捕获
org.springframework.dao.DuplicateKeyException
 */

@Mapper
public interface UserRoleMapper {

    /**
     * 根据userId、roleId删除用户角色
     *
     * @param userId
     * @param roleId
     */
    Long deleteUserRoleByUserIdRoleId(Long userId, Long roleId) throws Exception;

    /**
     * 根据userId删除用户角色
     *
     * @param userId
     */

    Long deleteUserRoleByUserId(Long userId) throws Exception;

    /**
     * 插入用户角色
     *
     * @param userRole
     * @return
     */
    Long insertUserRole(UserRole userRole) throws Exception;

    /**
     * 批量插入用户角色
     *
     * @param userRoleList
     * @return
     */
    Long batchInsertUserRole(List<UserRole> userRoleList);
}
