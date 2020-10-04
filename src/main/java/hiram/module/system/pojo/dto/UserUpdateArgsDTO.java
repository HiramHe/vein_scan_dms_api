package hiram.module.system.pojo.dto;

import hiram.module.system.pojo.po.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/10/1 19:05
 * @Description: ""
 */

@Data
public class UserUpdateArgsDTO {

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
