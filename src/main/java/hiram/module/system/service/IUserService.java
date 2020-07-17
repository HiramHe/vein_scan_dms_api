package hiram.module.system.service;

import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.domain.vo.UserInsertVO;
import hiram.module.system.domain.vo.UserListParam;
import hiram.module.system.domain.vo.UserUpdateParam;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:59
 * @Description: ""
 */

public interface IUserService {

    void insert(SysUser sysUser);

    SysUser selectUserByUsername(String username);

    List<SysUser> selectUserList(UserListParam userListParam);

    Long recoverDeletedUserById(Long userId);

    Long updateUser(UserUpdateParam userUpdateParam);

    Long logicallyDeleteUserByIds(Long[] userIds);

    Long logicallyDeleteUserById(Long userId);

    Long physicallyDeleteUserById(Long userId);

    Long recoverDeletedUserByIds(Long[] userIds);

    Long insertUser(UserInsertVO userInsertVO);

    boolean checkUserNameExist(String username);

    boolean checkPhoneUnique(SysUser user);

    boolean checkEmailUnique(SysUser user);
}
