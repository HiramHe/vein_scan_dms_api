package hiram.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import hiram.module.system.pojo.po.SysUser;
import org.junit.Test;

/**
 * @Author: HiramHe
 * @Date: 2020/5/5 14:33
 * @Description: ""
 */
public class FastjsonTest {

    JsonMapper jsonMapper = new JsonMapper();

    /**
     * 将对象转成了json格式的字符串
     * @throws JsonProcessingException
     */
    @Test
    public void testObject2String() throws JsonProcessingException {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("hhm");

        String s = jsonMapper.writeValueAsString(sysUser);

        System.out.println(s);
    }
}
