package hiram.module.image.controller;

import hiram.common.enums.ResultCode;
import hiram.common.pojo.ResultObject;
import hiram.common.web.controller.BaseController;
import hiram.common.web.domain.vo.TableData;
import hiram.module.image.domain.Infrared;
import hiram.module.image.service.InfraredService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/20 16:05
 * @Description: ""
 */

@Api(tags = "图像管理-红外图像接口")
@RestController
@RequestMapping("/infrared")
public class InfraredController extends BaseController {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private InfraredService infraredService;

    @GetMapping("/query/{id}")
    @ApiOperation(value = "根据红外图像id查询红外图像")
    public void selectOneById(@PathVariable("id") Long infraredId){
        Infrared infrared = infraredService.selectByInfraredId(infraredId);
        if(logger.isDebugEnabled()){
            if(infrared != null){
                logger.debug(infrared.getFilename());
            }
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询红外图像列表")
    public ResultObject<?> list(){

        startPage();
        List<Infrared> infrareds = infraredService.list();

        if(logger.isDebugEnabled()){
            logger.debug(infrareds);
        }

        TableData tableData = getTableData(infrareds);

        return ResultObject.success(ResultCode.SUCCESS,tableData);
    }
}
