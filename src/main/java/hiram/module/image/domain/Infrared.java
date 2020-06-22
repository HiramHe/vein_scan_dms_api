package hiram.module.image.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import hiram.common.web.domain.entity.BaseEntity;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: HiramHe
 * @Date: 2020/5/20 20:22
 * @Description: ""
 */

@Data
@TableName("infrared_image")
public class Infrared extends BaseEntity {
    @TableId
    private Long infraredId;
    @TableField(value = "perspective")
    private String perspective;
    private String filename;
    private String path;
    private Timestamp scanTime;
    private Long patientId;
    private Long userId;
}
