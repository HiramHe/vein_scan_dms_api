package hiram.module.system.pojo.vo;

import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/7/16 21:14
 * @Description: ""
 */

@Data
public class UserUpdateParam {

    private Long userId;

    private String nickname;

    private String sex;

    private String email;

    private String phoneNumber;

//    private String avatar;

    private String remark;

    private Boolean enabled;

    /** 角色组 */
    private Long[] roleIds;
}
