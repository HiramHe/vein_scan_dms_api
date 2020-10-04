package hiram.module.system.service.impl;

import hiram.module.system.pojo.po.SysRole;
import hiram.module.system.mapper.RoleMapper;
import hiram.module.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 14:43
 * @Description: ""
 */

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<SysRole> selectRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }
}
