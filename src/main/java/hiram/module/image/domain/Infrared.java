package hiram.module.image.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp scanTime;
    private Long patientId;
    private Long userId;
}
