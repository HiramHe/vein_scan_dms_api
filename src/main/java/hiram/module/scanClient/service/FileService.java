package hiram.module.scanClient.service;

import hiram.module.image.pojo.query.BUltrasoundServiceQuery;
import hiram.module.image.pojo.query.InfraredServiceQuery;
import hiram.module.image.pojo.query.InfraredDescriptionServiceQuery;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/25 20:51
 * @Description: ""
 */

public interface FileService {

    boolean upload2Local(MultipartFile infraredImage,
                      MultipartFile bUltrasoundImage,
                      InfraredServiceQuery infraredServiceQuery,
                      InfraredDescriptionServiceQuery infraredDescriptionServiceQuery,
                      BUltrasoundServiceQuery bUltrasoundServiceQuery
                      ) throws Exception;

    boolean saveFile(MultipartFile file,String fileName, String directoryStr) throws IOException;

    boolean saveFile(MultipartFile infraredImage,String infraredFileName,
                         MultipartFile bUltrasoundImage,String bUltrasoundFileName) throws IOException;

    boolean deleteFile(String fileName,String directoryStr);
}
