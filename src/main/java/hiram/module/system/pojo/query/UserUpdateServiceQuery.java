package hiram.module.system.pojo.query;

import hiram.module.system.pojo.po.UserRole;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/10/1 19:05
 * @Description: ""
 */

@Data
public class UserUpdateServiceQuery {

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

    /** 角色组 */
    private List<UserRole> userRoleList;

}
