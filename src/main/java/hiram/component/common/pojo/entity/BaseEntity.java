package hiram.component.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * @Author: HiramHe
 * @Date: 2020/4/23 20:32
 * @Description: "公共实体类"
 */

@Data
public class BaseEntity implements Serializable {

    //接口的参数是对象时，让本字段不显示在swagger中
    @ApiModelProperty(hidden = true)
    private boolean deleted = false;

    @ApiModelProperty(value = "创建时间:yyyy-MM-dd HH:mm:ss")
    //自动填充数据库生成的时间
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "修改时间:yyyy-MM-dd HH:mm:ss")
    @TableField(value = "gmt_modify",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModify;

    @TableField(value = "version")
    private Long version;
}
