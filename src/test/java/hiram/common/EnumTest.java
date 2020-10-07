package hiram.common;

import hiram.common.enums.ResultCodeEnum;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/15 20:33
 * @Description: ""
 */

public class EnumTest {

    @Test
    public void showEnum(){
        Map<Long,String> enums = new HashMap<>();
        ResultCodeEnum[] values = ResultCodeEnum.values();
        for (ResultCodeEnum value: values) {
            enums.put(value.getCode(),value.getMsg());
        }
        for (Map.Entry<Long,String> entry:enums.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
