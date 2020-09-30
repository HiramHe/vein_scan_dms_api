package hiram.module.scanClient.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.common.enums.ResultCode;
import hiram.common.utils.FileUtils;
import hiram.common.utils.MyStringUtils;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.properties.file.ImageProperties;
import hiram.module.image.pojo.dto.BUltrasoundDTO;
import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.dto.InfraredDescriptionDTO;
import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.pojo.entity.InfraredDescription;
import hiram.module.scanClient.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
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
    private ImageProperties imageProperties;

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

        //红外图像不得为空
        if (infraredImage ==null || infraredImage.isEmpty()){
            return ResultObject.failed(ResultCode.FILE_UPLOAD_INFRARED_EMPTY);
        }

        //校验是否是图片
        String infraredImageFileName = infraredImage.getOriginalFilename();
        if(!FileUtils.isImage(infraredImageFileName)){
            return ResultObject.failed(ResultCode.FILE_FORMAT_NOT_SUPPORT);
        }

        if ((xCoordinate==null && yCoordinate!=null) || (xCoordinate!=null && yCoordinate==null)){
            return ResultObject.failed(ResultCode.COORDINATE_WRONG);
        }

        //构建红外图像信息
        InfraredDTO infraredDTO = new InfraredDTO();
        infraredDTO.setPerspective(perspective);
        infraredDTO.setFilename(infraredImageFileName);
        infraredDTO.setPath(imageProperties.getInfraredDirectory());
        infraredDTO.setScanTime(scanTime);
        infraredDTO.setPatientId(patientId);
        infraredDTO.setUserId(userId);

        //构建B超图像信息
        BUltrasoundDTO bUltrasoundDTO = null;
        if(bUltrasoundImage!=null) {
            String bUltrasoundImageFileName = bUltrasoundImage.getOriginalFilename();
            if (!FileUtils.isImage(bUltrasoundImageFileName)){
                return ResultObject.failed(ResultCode.FILE_FORMAT_NOT_SUPPORT);
            }

            bUltrasoundDTO = new BUltrasoundDTO();
            bUltrasoundDTO.setFilename(bUltrasoundImageFileName);
            bUltrasoundDTO.setPath(imageProperties.getUltrasoundDirectory());
        }

        //构建红外图像描述对象
        InfraredDescriptionDTO infraredDescriptionDTO = null;
        if (!MyStringUtils.isEmpty(description) || !MyStringUtils.isEmpty(severityLevel)
                || (xCoordinate!=null && yCoordinate!=null)){

            infraredDescriptionDTO = new InfraredDescriptionDTO();
            infraredDescriptionDTO.setDescription(description);
            infraredDescriptionDTO.setSeverityLevel(severityLevel);
            infraredDescriptionDTO.setXCoordinate(xCoordinate);
            infraredDescriptionDTO.setYCoordinate(yCoordinate);
        }


        //保存数据
        try {
            fileService.upload2Local(infraredImage,bUltrasoundImage,infraredDTO,infraredDescriptionDTO,bUltrasoundDTO);
        } catch (IOException e) {

            return ResultObject.failed(ResultCode.EXCEPTION_IO);
        } catch (DataAccessException e) {
            //删除磁盘上的文件
            if (infraredDTO != null){
                fileService.deleteFile(infraredDTO.getFilename(),infraredDTO.getPath());
            }
            if (bUltrasoundDTO != null){
                fileService.deleteFile(bUltrasoundDTO.getFilename(),bUltrasoundDTO.getPath());
            }

            return ResultObject.failed(ResultCode.EXCEPTION_DAO);
        } catch (Exception e) {

            return ResultObject.failed(ResultCode.EXCEPTION_SERVER);
        }

        return ResultObject.success(ResultCode.SUCCESS_UPLOAD);
    }
}
