package hiram.module.image.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: HiramHe
 * @Date: 2020/5/20 20:22
 * @Description: ""
 */

@Data
public class Infrared extends BaseEntity {
    private Long infraredId;
    private String perspective;
    private String filename;
    private String path;
    private LocalDateTime scanTime;
    private Long patientId;
    private Long userId;
}
