package hiram.module.system.domain.vo;

import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 21:18
 * @Description: "修改密码的vo"
 */

@Data
public class UserPasswordVo {

    private String oldPassword;

    private String newPassword;
}
