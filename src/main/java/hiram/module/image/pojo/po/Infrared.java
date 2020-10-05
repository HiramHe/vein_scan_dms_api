package hiram.module.image.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: HiramHe
 * @Date: 2020/5/20 20:22
 * @Description: ""
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("infrared_image")
public class Infrared extends BaseEntity {
    private Long infraredId;
    private String perspective;
    private String filename;
    private String path;
    private LocalDateTime scanTime;
    private Long patientId;
    private Long userId;
}
