package hiram.module.system.pojo.dto;

import hiram.module.system.pojo.entity.SysRole;
import hiram.module.system.pojo.entity.SysUser;
import lombok.Data;

import java.util.List;

/** DTO: 控制层与业务层传输的数据
 * @Author: HiramHe
 * @Date: 2020/4/23 21:09
 * @Description: ""
 */

@Data
public class UserDto extends SysUser {

    List<SysRole> roles;
}