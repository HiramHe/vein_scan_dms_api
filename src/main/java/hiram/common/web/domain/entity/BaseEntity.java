package hiram.common.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


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

    @TableField(value = "gmt_create")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp gmtCreate;

    @TableField(value = "gmt_modify")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp gmtModify;

    @TableField(value = "version")
    private Long version;
}
