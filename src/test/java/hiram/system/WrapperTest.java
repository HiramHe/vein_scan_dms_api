package hiram.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.module.system.pojo.entity.SysUser;
import hiram.module.system.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 9:44
 * @Description: ""
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads(){
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","Hiram");

        SysUser sysUser = userMapper.selectOne(queryWrapper);
        System.out.println(sysUser);
    }
}
