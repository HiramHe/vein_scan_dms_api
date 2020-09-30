package hiram.module.image.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:28
 * @Description: ""
 */

@Data
public class InfraredDTO {

    private String perspective;
    private String filename;
    private String path;
    private Date scanTime;
    private Long patientId;
    private Long userId;
}
