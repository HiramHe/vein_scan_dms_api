package hiram.module.scanClient.service;

import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.pojo.entity.InfraredDescription;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/25 20:51
 * @Description: ""
 */

public interface FileService {

    void upload2Local(MultipartFile infraredImage,
                      MultipartFile bUltrasoundImage,
                      Infrared infrared,
                      InfraredDescription infraredDescription,
                      BUltrasound bUltrasound
                      ) throws IOException;
}
