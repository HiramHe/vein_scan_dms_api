package hiram.common.web.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


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
    private Timestamp gmtCreate;

    @TableField(value = "gmt_modify")
    private Timestamp gmtModify;

    @TableField(value = "version")
    private Long version;
}
