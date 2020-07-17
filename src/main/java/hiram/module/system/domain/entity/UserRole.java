package hiram.module.system.domain.entity;

import hiram.common.web.domain.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/4/24 10:46
 * @Description: ""
 */

@Data
public class UserRole extends BaseEntity {

    /** 用户ID */

    private Long userId;

    /** 角色ID */
    private Long roleId;
}
