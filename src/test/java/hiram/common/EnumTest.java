package hiram.common;

import hiram.common.web.ResultCode;
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
        ResultCode[] values = ResultCode.values();
        for (ResultCode value: values) {
            enums.put(value.getCode(),value.getMsg());
        }
        for (Map.Entry<Long,String> entry:enums.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
