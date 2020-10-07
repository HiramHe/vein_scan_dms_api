package hiram.module.scanClient.controller;

import hiram.common.enums.ResultCodeEnum;
import hiram.common.utils.FileUtils;
import hiram.common.utils.MyStringUtils;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.properties.file.ImageProperties;
import hiram.module.image.pojo.query.BUltrasoundServiceQuery;
import hiram.module.image.pojo.query.InfraredServiceQuery;
import hiram.module.image.pojo.query.InfraredDescriptionServiceQuery;
import hiram.module.scanClient.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

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

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ImageProperties imageProperties;

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传图像", hidden = false)
    @PutMapping("/upload")
    public ResultObject<?> uploadWithSingleBUltra(@RequestParam MultipartFile infraredImage,
                                       MultipartFile bUltrasoundImage,
                                       String perspective,
                                       @ApiParam(value = "yyyy-MM-dd HH:mm:ss")
                                       @RequestParam(required = false)
                                           @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                   LocalDateTime scanTime,
                                       @RequestParam Long patientId,
                                       @RequestParam(required = false) Long userId,
                                       String description,
                                       String severityLevel,
                                       Long descriptionXCoordinate,
                                       Long descriptionYCoordinate,
                                       Long bUltrasoundXCoordinate,
                                       Long bUltrasoundYCoordinate
                                       ){

        //红外图像不得为空
        if (infraredImage ==null || infraredImage.isEmpty()){
            return ResultObject.failed(ResultCodeEnum.FILE_UPLOAD_INFRARED_EMPTY);
        }

        //校验是否是图片
        String infraredImageFileName = infraredImage.getOriginalFilename();
        if(!FileUtils.isImage(infraredImageFileName)){
            return ResultObject.failed(ResultCodeEnum.FILE_FORMAT_NOT_SUPPORT);
        }

        //校验坐标
        if ((descriptionXCoordinate==null && descriptionYCoordinate!=null) || (descriptionXCoordinate!=null && descriptionYCoordinate==null)){
            return ResultObject.failed(ResultCodeEnum.COORDINATE_WRONG);
        }
        if ((bUltrasoundXCoordinate==null && bUltrasoundYCoordinate!=null) || (bUltrasoundXCoordinate!=null && bUltrasoundYCoordinate==null)){
            return ResultObject.failed(ResultCodeEnum.COORDINATE_WRONG);
        }

        //构建红外图像信息
        InfraredServiceQuery infraredServiceQuery = new InfraredServiceQuery();
        infraredServiceQuery.setPerspective(perspective);
        infraredServiceQuery.setFilename(infraredImageFileName);
        infraredServiceQuery.setPath(imageProperties.getInfraredDirectory());
        infraredServiceQuery.setScanTime(scanTime);
        infraredServiceQuery.setPatientId(patientId);
        infraredServiceQuery.setUserId(userId);

        //构建B超图像信息
        BUltrasoundServiceQuery bUltrasoundServiceQuery = null;
        if(bUltrasoundImage!=null) {
            String bUltrasoundImageFileName = bUltrasoundImage.getOriginalFilename();
            if (!FileUtils.isImage(bUltrasoundImageFileName)){
                return ResultObject.failed(ResultCodeEnum.FILE_FORMAT_NOT_SUPPORT);
            }

            bUltrasoundServiceQuery = new BUltrasoundServiceQuery();
            bUltrasoundServiceQuery.setFilename(bUltrasoundImageFileName);
            bUltrasoundServiceQuery.setPath(imageProperties.getUltrasoundDirectory());
        }

        //构建红外图像描述对象
        InfraredDescriptionServiceQuery infraredDescriptionServiceQuery = null;
        if (!MyStringUtils.isEmpty(description) || !MyStringUtils.isEmpty(severityLevel)
                || (descriptionXCoordinate!=null && descriptionYCoordinate!=null)
                || (bUltrasoundXCoordinate!=null && bUltrasoundYCoordinate!=null)){

            infraredDescriptionServiceQuery = new InfraredDescriptionServiceQuery();
            infraredDescriptionServiceQuery.setDescription(description);
            infraredDescriptionServiceQuery.setSeverityLevel(severityLevel);
            infraredDescriptionServiceQuery.setDescriptionXCoordinate(descriptionXCoordinate);
            infraredDescriptionServiceQuery.setDescriptionYCoordinate(descriptionYCoordinate);
            infraredDescriptionServiceQuery.setBUltrasoundXCoordinate(bUltrasoundXCoordinate);
            infraredDescriptionServiceQuery.setBUltrasoundYCoordinate(bUltrasoundYCoordinate);
        }


        //保存数据
        try {

            fileService.upload2Local(infraredImage,bUltrasoundImage, infraredServiceQuery, infraredDescriptionServiceQuery, bUltrasoundServiceQuery);

        } catch (IOException e) {

            return ResultObject.failed(ResultCodeEnum.EXCEPTION_IO);

        } catch (DataAccessException e) {

            if (logger.isDebugEnabled()){
                logger.debug(e);
            }

            //删除磁盘上的文件
            fileService.deleteFile(infraredServiceQuery.getFilename(), infraredServiceQuery.getPath());

            if (bUltrasoundServiceQuery != null){
                fileService.deleteFile(bUltrasoundServiceQuery.getFilename(), bUltrasoundServiceQuery.getPath());
            }

            return ResultObject.failed(ResultCodeEnum.EXCEPTION_DAO);

        } catch (Exception e) {
            if (logger.isDebugEnabled()){
                logger.debug(e);
            }

            return ResultObject.failed(ResultCodeEnum.EXCEPTION_SERVER);
        }

        return ResultObject.success(ResultCodeEnum.SUCCESS_UPLOAD);
    }

//    @ApiOperation(value = "单红外图像多B超图像上传")
//    @PutMapping("/uploadWithMultiBUltra")
//    public void uploadWithMultiBUltra(@RequestParam MultipartFile infraredImage,
//                                      MultipartFile[] bUltrasoundImages,
//                                      String perspective,
//                                      @ApiParam(value = "yyyy-MM-dd HH:mm:ss")
//                                      @RequestParam(required = false)
//                                      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//                                              LocalDateTime scanTime,
//                                      @RequestParam Long patientId,
//                                      @RequestParam(required = false) Long userId,
//                                      String description,
//                                      String severityLevel,
//                                      Long descriptionXCoordinate,
//                                      Long descriptionYCoordinate
////                                      Coordinate[] bUltrasoundCoordinates
//                                      ){
//
//        System.out.println();
//
//    }
}
