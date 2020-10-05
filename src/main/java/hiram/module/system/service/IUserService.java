package hiram.module.system.service;

import hiram.module.system.pojo.dto.UserSelectDTO;
import hiram.module.system.pojo.query.UserInsertServiceQuery;
import hiram.module.system.pojo.query.UserListServiceQuery;
import hiram.module.system.pojo.query.UserUpdateServiceQuery;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.query.UserInsertViewQuery;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:59
 * @Description: ""
 */

public interface IUserService {

    /*查询*/
    SysUser selectUserByUsername(String username);

    List<UserSelectDTO> selectUserList(UserListServiceQuery userListServiceQuery);

    UserSelectDTO selectUserByUserId(Long userId);

    /*恢复*/
    Long recoverDeletedUserById(Long userId);

    Long recoverDeletedUserByIds(Long[] userIds);

    /*更新*/
    Long updateUser(UserUpdateServiceQuery userUpdateServiceQuery) throws Exception;

    /*删除*/
    Long logicallyDeleteUserByIds(Long[] userIds);

    Long logicallyDeleteUserById(Long userId);

    Long physicallyDeleteUserById(Long userId);

    /*插入*/
    SysUser insertUser(UserInsertServiceQuery userInsertServiceQuery);

    /*校验*/

    boolean checkUserNameUnique(Long userId,String username);

    boolean checkPhoneUnique(Long userId,String phoneNumber);

    boolean checkEmailUnique(Long userId,String email);

    /*重置密码*/
    int resetUserPwd(Long userId, String newPassword);
}
