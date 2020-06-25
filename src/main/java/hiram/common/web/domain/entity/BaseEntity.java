package hiram.common.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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

    @TableField(value = "deleted")
    private boolean deleted = false;

    @TableField(value = "gmt_create")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Timestamp gmtCreate;

    @TableField(value = "gmt_modify")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Timestamp gmtModify;

    @TableField(value = "version")
    private Long version;
}
