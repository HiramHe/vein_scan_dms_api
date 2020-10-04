package hiram.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hiram.module.system.pojo.dto.UserQueryArgsDTO;
import hiram.module.system.pojo.dto.UserQueryRtDTO;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.vo.UserUpdateArgsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:53
 * @Description: ""
 */

/*
查询：结果有可能是多个表连接的结果，因此参数可能是一种dto，返回值是另一种dto
插入：不存在ResultMap，会把数据库自动生成的字段值映射到parameterType对应的类对象中，因此传入的参数直接使用po类，返回的值是数值，表示
影响的函数
 */

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    //查询
    List<UserQueryRtDTO> selectUserList(UserQueryArgsDTO userQueryArgsDTO);

    //恢复
    Long recoverDeletedUserById(Long userId);

    Long recoverDeletedUserByIds(Long[] userIds);

    //更新
    Long updateUser(SysUser sysUser);

    //删除
    Long logicallyDeleteUserByIds(Long[] userIds);

    Long logicallyDeleteUserById(Long userId);

    Long physicallyDeleteUserById(Long userId);

    //插入
    Long insertUser(SysUser sysUser);

    //校验
    SysUser checkUserNameUnique(String username);

    SysUser checkPhoneUnique(String phoneNumber);

    SysUser checkEmailUnique(String email);

    //重置密码
    int resetUserPwd(Long userId, String newPassword);
}
