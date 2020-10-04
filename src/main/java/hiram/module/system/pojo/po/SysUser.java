package hiram.module.system.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.component.common.pojo.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * 实现UserDetails接口，这样我们的SysUser就可以直接拿到spring security中使用了
 */

@Data
@TableName(value = "user")
@ApiModel(description = "系统用户")
public class SysUser extends BaseEntity{

    private Long userId;

    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    private String password;

    private String nickname;

    private String realName;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private LocalDate birthday;

    private String email;

    private String phoneNumber;

    private String avatar;

    private String remark;

    private Boolean enabled;

    public SysUser(){}

    public SysUser(String username) {
        this.username = username;
    }

    //仅供LoginUser使用
    public boolean getEnabled() {

        if (enabled == null){
            return true;
        }

        return this.enabled;
    }
}
