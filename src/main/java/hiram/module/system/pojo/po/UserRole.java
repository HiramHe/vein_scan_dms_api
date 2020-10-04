package hiram.module.system.pojo.po;

import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/10/1 14:41
 * @Description: ""
 */

@Data
public class UserRole extends BaseEntity {

    /** 用户ID */

    private Long userId;

    /** 角色ID */
    private Long roleId;
}
