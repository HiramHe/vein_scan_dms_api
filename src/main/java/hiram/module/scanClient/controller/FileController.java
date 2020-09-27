package hiram.module.scanClient.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.common.enums.ResultCode;
import hiram.common.utils.FileUtils;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.pojo.entity.InfraredDescription;
import hiram.module.scanClient.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: HiramHe
 * @Date: 2020/7/20 17:43
 * @Description: ""
 */

/*
@RequestBody：前端的content-type:"application/json",
表单提交：前端的content-type:"application/json",
 */

@Api(tags = "扫描客户端接口")
@RestController
@RequestMapping("/client")
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传图像", hidden = false)
    @PutMapping("/upload")
    public ResultObject<?> uploadImage(@RequestParam(required = true) MultipartFile infraredImage,
                                       MultipartFile bUltrasoundImage,
                                       String perspective,
                                       @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
                                       Date scanTime,
                                       @RequestParam(required = true)Long patientId,
                                       @RequestParam(required = true)Long userId,
                                       String description,
                                       String severityLevel,
                                       Long xCoordinate,
                                       Long yCoordinate
                                       ){

        if (infraredImage ==null || infraredImage.isEmpty()){
            return ResultObject.failed(ResultCode.FILE_UPLOAD_INFRARED_EMPTY);
        }

        String infraredImageFileName = infraredImage.getOriginalFilename();
        if(!FileUtils.isImage(infraredImageFileName)){
            return ResultObject.failed(ResultCode.FILE_FORMAT_NOT_SUPPORT);
        }

        BUltrasound bUltrasound = null;
        if(bUltrasoundImage!=null) {
            String bUltrasoundImageFileName = bUltrasoundImage.getOriginalFilename();
            if (!FileUtils.isImage(bUltrasoundImageFileName)){
                return ResultObject.failed(ResultCode.FILE_FORMAT_NOT_SUPPORT);
            }

            bUltrasound = new BUltrasound();
            bUltrasound.setFilename(bUltrasoundImageFileName);
        }

        Infrared infrared = new Infrared();
        infrared.setPerspective(perspective);
        infrared.setFilename(infraredImageFileName);
        infrared.setScanTime(scanTime);
        infrared.setPatientId(patientId);
        infrared.setUserId(userId);

        InfraredDescription infraredDescription = new InfraredDescription();
        infraredDescription.setDescription(description);
        infraredDescription.setSeverityLevel(severityLevel);
        infraredDescription.setXCoordinate(xCoordinate);
        infraredDescription.setYCoordinate(yCoordinate);

        try {
            fileService.upload2Local(infraredImage,bUltrasoundImage,infrared,infraredDescription,bUltrasound);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
