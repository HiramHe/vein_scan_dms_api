package hiram.module.system.pojo.dto;

import hiram.component.common.pojo.entity.BaseEntity;
import hiram.module.system.pojo.po.SysRole;
import lombok.Data;

import javax.validation.constraints.Size;
import java.sql.Timestamp;
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
public class UserInsertArgsDTO {

    private String username;

    private String password;

    private String nickname;

    private String realName;

    private String sex;

    private Timestamp birthday;

    private String email;

    private String phoneNumber;

    private String avatar;

    private String remark;

    private boolean enabled = true;
}
