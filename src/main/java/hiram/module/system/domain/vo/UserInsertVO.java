package hiram.module.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.module.system.domain.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/7/17 10:51
 * @Description: ""
 */

@Data
public class UserInsertVO {

    @ApiModelProperty(hidden = true)
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String realName;

    private String sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Timestamp birthday;

    private String email;

    private String phoneNumber;

    @ApiModelProperty(hidden = true)
    private String avatar;

    private String remark;

    private boolean enabled;

    @ApiModelProperty(hidden = true)
    @TableField(exist = false)
    private List<Long> roleIds;
}
