package hiram.module.scanClient.service.impl;

import hiram.component.properties.file.ImageProperties;
import hiram.module.image.pojo.dto.BUltrasoundDTO;
import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.dto.InfraredDescriptionDTO;
import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.pojo.entity.InfraredDescription;
import hiram.module.image.service.BUltrasoundService;
import hiram.module.image.service.InfraredDescriptionService;
import hiram.module.image.service.InfraredService;
import hiram.module.scanClient.service.FileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/25 20:53
 * @Description: ""
 */

@Service
public class FileServiceImpl implements FileService {

    Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ImageProperties imageProperties;

    @Autowired
    InfraredService infraredService;

    @Autowired
    BUltrasoundService bUltrasoundService;

    @Autowired
    InfraredDescriptionService infraredDescriptionService;

    @Override
    @Transactional
    public boolean upload2Local(MultipartFile infraredImage,
                             MultipartFile bUltrasoundImage,
                             InfraredDTO infraredDTO,
                             InfraredDescriptionDTO infraredDescriptionDTO,
                             BUltrasoundDTO bUltrasoundDTO) throws DataAccessException, IOException {

        //保存红外图像
        if (infraredImage != null){
            saveFile(infraredImage,infraredDTO.getFilename(),imageProperties.getInfraredDirectory());
        }

        //保存B超图像
        if (bUltrasoundImage != null){
            saveFile(bUltrasoundImage,bUltrasoundDTO.getFilename(),imageProperties.getUltrasoundDirectory());
        }

        //插入红外图像记录
        Infrared infrared = null;
        if (infraredImage != null){
            infrared = infraredService.insertOne(infraredDTO);
        }

        //插入B超图像记录
        BUltrasound bUltrasound = null;
        if (bUltrasoundImage != null){
            bUltrasound = bUltrasoundService.insertOne(bUltrasoundDTO);
        }

        //插入红外图像描述记录
        InfraredDescription infraredDescription = null;
        if (infraredDescriptionDTO != null){
            if (infrared != null){
                infraredDescriptionDTO.setInfraredId(infrared.getInfraredId());
            }
            if (bUltrasound != null){
                infraredDescriptionDTO.setBUltrasoundId(bUltrasound.getBUltrasoundId());
            }

            infraredDescription = infraredDescriptionService.insertOne(infraredDescriptionDTO);
        }

        if (logger.isDebugEnabled()){
            if (infraredDescription != null){

                logger.debug(infraredDescription.toString());
            }
        }

        return true;

    }

    @Override
    public boolean saveFile(MultipartFile file,String fileName, String directoryStr) throws IOException {

        //判断文件夹是否存在，不存在则新建
        File directory = new File(directoryStr);
        if(!directory.exists() && !directory.isDirectory() ){
            directory.mkdirs();
        }

        //将文件写入磁盘
        File fileDest = new File(directoryStr + fileName);
        file.transferTo(fileDest);

        return true;

    }

    //用不上
    @Override
    public boolean saveFile(MultipartFile infraredImage,String infraredFileName,
                            MultipartFile bUltrasoundImage,String bUltrasoundFileName) throws IOException {


        String infraredDirectoryStr = imageProperties.getInfraredDirectory();
        //判断红外文件夹是否存在，不存在则新建
        File infraredDirectory = new File(infraredDirectoryStr);
        if(!infraredDirectory.exists() && !infraredDirectory.isDirectory() ){
            infraredDirectory.mkdirs();
        }
        //将红外图像写入磁盘
        File infraredImageDest = new File(infraredDirectoryStr + infraredFileName);
        infraredImage.transferTo(infraredImageDest);


        String bUltrasoundDirectoryStr = imageProperties.getUltrasoundDirectory();
        //判断B超文件夹是否存在，不存在则新建
        File bUltrasoundDirectory = new File(bUltrasoundDirectoryStr);
        if(!bUltrasoundDirectory.exists() && !bUltrasoundDirectory.isDirectory() ){
            bUltrasoundDirectory.mkdirs();
        }
        //将B超图像写入磁盘
        File bUltrasoundImageDest = new File(bUltrasoundDirectoryStr + bUltrasoundFileName);
        bUltrasoundImage.transferTo(bUltrasoundImageDest);

        return true;

    }

    @Override
    public boolean deleteFile(String fileName, String directoryStr)  {

        String fileDest = directoryStr + fileName;
        File file = new File(fileDest);

        boolean deleted = true;
        if (file.exists()){
            deleted = file.delete();
        } else {
            if (logger.isDebugEnabled()){
                logger.debug("文件【" + fileDest + "】不存在");
            }
        }

        return deleted;
    }
}
