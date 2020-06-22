package hiram.common.web.controller;

import hiram.common.web.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 17:25
 * @Description: ""
 */

@Api(tags = "完全公开接口")
@RestController
@RequestMapping("/common")
public class CommonController {

    private Log logger = LogFactory.getLog(getClass());

    @GetMapping("/test")
    public String test(){
        String msg = "test controller.";
        if(logger.isDebugEnabled()){
            System.out.println("test controller.");
        }

        return msg;
    }

    /*
    让前端随时查看ResultCode的含义。
     */
    @ApiOperation(value = "查看接口返回码的定义")
    @GetMapping("/ResultObjectCodes")
    public Map<Object,Object> showResultCodes(){
        ResultCode[] resultCodes = ResultCode.values();

        Map<Object,Object> code_msg = new HashMap<>();
        code_msg.put("code","msg");
        for (ResultCode resultCode:resultCodes) {
            code_msg.put(resultCode.getCode(),resultCode.getMsg());
        }

        return code_msg;
    }
}
