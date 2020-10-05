package hiram.module.image.controller;

import hiram.common.enums.ResultCode;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.common.controller.BaseController;
import hiram.component.common.pojo.vo.TableData;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.pojo.query.InfraredListViewQuery;
import hiram.module.image.service.InfraredService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/20 16:05
 * @Description: ""
 */

@Api(tags = "红外图像接口")
@RestController
@RequestMapping("/infrared")
public class InfraredController extends BaseController {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private InfraredService infraredService;

    @ApiOperation(value = "根据红外图像id查询红外图像",hidden = true)
    @GetMapping("/query/{id}")
    public void selectOneById(@PathVariable("id") Long infraredId){
        Infrared infrared = infraredService.selectByInfraredId(infraredId);
        if(logger.isDebugEnabled()){
            if(infrared != null){
                logger.debug(infrared.getFilename());
            }
        }
    }

    @ApiOperation(value = "查询红外图像列表",hidden = true)
    @GetMapping("/list/{pageNum}/{pageSize}")
    public ResultObject<?> list(
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize,
            InfraredListViewQuery infraredListViewQuery){

        this.startPage();
        List<Infrared> infrareds = infraredService.list(infraredListViewQuery);

        if(logger.isDebugEnabled()){
            logger.debug(infrareds);
        }

        TableData tableData = this.getTableData(infrareds);

        return ResultObject.success(ResultCode.SUCCESS,tableData);
    }
}
