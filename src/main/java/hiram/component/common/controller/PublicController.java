package hiram.component.common.controller;

import hiram.common.enums.ResultCodeEnum;
import hiram.common.enums.ResourcePatternLocationEnum;
import hiram.component.common.pojo.vo.ResultObject;
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
@RequestMapping("/public")
public class PublicController {

    private Log logger = LogFactory.getLog(getClass());

    /*
    让前端随时查看ResultCode中的枚举项。
     */
    @ApiOperation(value = "查看接口返回码的定义")
    @GetMapping("/resultCodes")
    public ResultObject<Map<String,Object>> listResultCode(){

        Map<Long,String> resultCodes = new HashMap<>();
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            resultCodes.put(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
        }

        Map<String,Object> data = new HashMap<>();
        data.put("code:msg",resultCodes);

        return ResultObject.success(ResultCodeEnum.SUCCESS,data);
    }

    /*
    让前端随时查看ResourcePatternLocation中的枚举项。
     */
    @ApiOperation(value = "查看资源映射路径")
    @GetMapping("/resourcePatternLocations")
    public ResultObject<Map<String,Object>> listResourcePatternLocation(){

        Map<String,String> resourcePatternLocations = new HashMap<>();
        for (ResourcePatternLocationEnum resourcePatternLocationEnum : ResourcePatternLocationEnum.values()) {
            resourcePatternLocations.put(resourcePatternLocationEnum.getName(), resourcePatternLocationEnum.getPathPattern());
        }

        Map<String,Object> data = new HashMap<>();
        data.put("resource:pathPattern",resourcePatternLocations);

        return ResultObject.success(ResultCodeEnum.SUCCESS,data);
    }
}
