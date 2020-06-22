package hiram.module.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import hiram.common.web.domain.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 实现UserDetails接口，这样我们的SysUser就可以直接拿到spring security中使用了
 */

@Data
@TableName(value = "user")
public class SysUser extends BaseEntity{

    @TableId(value="user_id",type = IdType.NONE)
    private Long userId;

    @TableField(value = "username")
    private String username;

    private String password;

    private String nickname;

    private String realName;

    private String sex;

    private Date birthday;

    private String email;

    @TableField(value="phone_number")
    private String phoneNumber;

    private String avatar;

    private String remark;

    private boolean enabled;

    private List<SysRole> roles;

}
