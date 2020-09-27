package hiram.module.system.service;

import hiram.module.system.pojo.entity.SysUser;
import hiram.module.system.pojo.vo.UserInsertVO;
import hiram.module.system.pojo.vo.UserListParam;
import hiram.module.system.pojo.vo.UserUpdateParam;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:59
 * @Description: ""
 */

public interface IUserService {

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

    int resetUserPwd(Long userId, String newPassword);
}
