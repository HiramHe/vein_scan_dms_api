package hiram.common;

import hiram.common.utils.token.JwtUtils;
import hiram.module.system.pojo.po.SysUser;
import org.junit.Test;

/**
 * @Author: HiramHe
 * @Date: 2020/5/5 14:49
 * @Description: ""
 */
public class JwtTest {

    @Test
    public void jwtGenerate() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("Hiram");
        String token = JwtUtils.generateTokenExpireInMinutes(sysUser,60*2);
        System.out.println(token);
        token = "fsfsdfsdfd";

        try {
            System.out.println(JwtUtils.isTokenExpired(token));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
