package hiram.module.system.service;

import hiram.module.system.pojo.dto.UserQueryRtDTO;
import hiram.module.system.pojo.dto.UserUpdateArgsDTO;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.vo.UserInsertArgsVO;
import hiram.module.system.pojo.vo.UserQueryArgsVO;
import hiram.module.system.pojo.vo.UserUpdateArgsVO;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:59
 * @Description: ""
 */

public interface IUserService {

    /*查询*/
    SysUser selectUserByUsername(String username);

    List<UserQueryRtDTO> selectUserList(UserQueryArgsVO userQueryArgsVO);

    /*恢复*/
    Long recoverDeletedUserById(Long userId);

    Long recoverDeletedUserByIds(Long[] userIds);

    /*更新*/
    Long updateUser(UserUpdateArgsDTO userUpdateArgsDTO) throws Exception;

    /*删除*/
    Long logicallyDeleteUserByIds(Long[] userIds);

    Long logicallyDeleteUserById(Long userId);

    Long physicallyDeleteUserById(Long userId);

    /*插入*/
    SysUser insertUser(UserInsertArgsVO userInsertArgsVO);

    /*校验*/

    boolean checkUserNameUnique(Long userId,String username);

    boolean checkPhoneUnique(Long userId,String phoneNumber);

    boolean checkEmailUnique(Long userId,String email);

    /*重置密码*/
    int resetUserPwd(Long userId, String newPassword);
}
