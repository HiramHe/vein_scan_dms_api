package hiram.system;

import hiram.module.system.domain.entity.SysRole;
import hiram.module.system.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/7 14:48
 * @Description: ""
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleMapperTest {

    @Autowired
    RoleMapper roleMapper;

    @Test
    public void selectRolesByUsernameTest(){
        String username = "Hiram";
        List<SysRole> sysRoles = roleMapper.selectRolesByUsername(username);
        sysRoles.forEach(System.out::println);

        for (SysRole role:
             sysRoles) {
            System.out.println(role.getGmtCreate());
        }
    }
}
