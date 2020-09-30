package hiram.module.scanClient.service;

import hiram.module.image.pojo.dto.BUltrasoundDTO;
import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.dto.InfraredDescriptionDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/25 20:51
 * @Description: ""
 */

public interface FileService {

    boolean upload2Local(MultipartFile infraredImage,
                      MultipartFile bUltrasoundImage,
                      InfraredDTO infraredDTO,
                      InfraredDescriptionDTO infraredDescriptionDTO,
                      BUltrasoundDTO bUltrasoundDTO
                      ) throws Exception;

    boolean saveFile(MultipartFile file,String fileName, String directoryStr) throws IOException;

    boolean saveFile(MultipartFile infraredImage,String infraredFileName,
                         MultipartFile bUltrasoundImage,String bUltrasoundFileName) throws IOException;

    boolean deleteFile(String fileName,String directoryStr);
}
