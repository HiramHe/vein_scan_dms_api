package hiram.module.scanClient.service.impl;

import hiram.component.properties.file.ImageProperties;
import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.pojo.entity.InfraredDescription;
import hiram.module.scanClient.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/25 20:53
 * @Description: ""
 */

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private ImageProperties imageProperties;

    @Override
    public void upload2Local(MultipartFile infraredImage,
                             MultipartFile bUltrasoundImage,
                             Infrared infrared,
                             InfraredDescription infraredDescription,
                             BUltrasound bUltrasound) throws IOException {

        //确保文件夹路径以 "/" 为结束
        String infraredDirectoryStr = imageProperties.getInfraredDirectory().endsWith("/") ?
                imageProperties.getInfraredDirectory() : imageProperties.getInfraredDirectory() + "/";
        //判断红外文件夹是否存在，不存在则新建
        File infraredDirectory = new File(infraredDirectoryStr);
        if(!infraredDirectory.exists() && !infraredDirectory.isDirectory() ){
            infraredDirectory.mkdirs();
        }

        String bUltrasoundDirectoryStr = imageProperties.getUltrasoundDirectory().endsWith("/") ?
                imageProperties.getUltrasoundDirectory() : imageProperties.getUltrasoundDirectory() + "/";
        //判断B超文件夹是否存在，不存在则新建
        File bUltrasoundDirectory = new File(bUltrasoundDirectoryStr);
        if(!bUltrasoundDirectory.exists() && !bUltrasoundDirectory.isDirectory() ){
            bUltrasoundDirectory.mkdirs();
        }

        //将红外图像写入磁盘
        File infraredImageDest = new File(infraredDirectoryStr + infrared.getFilename());
        infraredImage.transferTo(infraredImageDest);
    }
}
