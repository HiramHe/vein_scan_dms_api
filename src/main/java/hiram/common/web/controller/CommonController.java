package hiram.common.web.controller;

import hiram.common.enums.ResultCode;
import hiram.common.pojo.ResultObject;
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

@Api(tags = "公开接口")
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
    让前端随时查看ResultCode中的枚举项。
     */
    @ApiOperation(value = "查看接口返回码的定义")
    @GetMapping("/ResultCode/show")
    public ResultObject<Map<String,Object>> showResultCodes(){

        Map<Long,String> resultCodes = new HashMap<>();
        for (ResultCode resultCode : ResultCode.values()) {
            resultCodes.put(resultCode.getCode(),resultCode.getMsg());
        }

        Map<String,Object> data = new HashMap<>();
        data.put("code:msg",resultCodes);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }
}
