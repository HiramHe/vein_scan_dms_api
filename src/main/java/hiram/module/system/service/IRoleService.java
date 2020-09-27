package hiram.module.system.service;

import hiram.module.system.pojo.entity.SysRole;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 14:43
 * @Description: ""
 */
public interface IRoleService {

    List<SysRole> selectRolesByUsername(String username);
}
