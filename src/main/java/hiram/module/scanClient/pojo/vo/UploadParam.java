package hiram.module.scanClient.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

/**
 * @Author: HiramHe
 * @Date: 2020/9/25 15:54
 * @Description: ""
 */

@Data
public class UploadParam {

    private MultipartFile infraredImage;

    private String perspective;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp scanTime;
    private Long patientId;
    private Long userId;

    private MultipartFile bUltrasoundImage;

    private String description;
    private String severrityLevel;
    private int xCoordinate;
    private int yCoordinate;

}
