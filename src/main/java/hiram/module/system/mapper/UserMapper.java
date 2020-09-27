package hiram.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hiram.module.system.pojo.entity.SysUser;
import hiram.module.system.pojo.vo.UserInsertVO;
import hiram.module.system.pojo.vo.UserListParam;
import hiram.module.system.pojo.vo.UserUpdateParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:53
 * @Description: ""
 */

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUserList(UserListParam userListParam);

    Long recoverDeletedUserById(Long userId);

    Long updateUser(UserUpdateParam userUpdateParam);

    Long logicallyDeleteUserByIds(Long[] userIds);

    Long logicallyDeleteUserById(Long userId);

    Long physicallyDeleteUserById(Long userId);

    Long recoverDeletedUserByIds(Long[] userIds);

    Long insertUser(UserInsertVO userInsertVO);

    Long checkUserNameExist(String username);

    SysUser checkPhoneUnique(String phoneNumber);

    SysUser checkEmailUnique(String phoneNumber);

    int resetUserPwd(Long userId, String newPassword);
}
