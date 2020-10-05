package hiram.module.system.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.component.common.pojo.entity.BaseEntity;
import hiram.module.system.pojo.po.SysRole;
import hiram.module.system.pojo.po.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/7/17 10:51
 * @Description: ""
 */

/*
rt = return
 */

@Data
public class UserSelectDTO extends BaseEntity {

    private Long userId;

    private String username;

    private String nickname;

    private String realName;

    private String sex;

    private LocalDate birthday;

    private String email;

    private String phoneNumber;

    private String avatar;

    private String remark;

    private Boolean enabled;

    private List<SysRole> roles;
}
