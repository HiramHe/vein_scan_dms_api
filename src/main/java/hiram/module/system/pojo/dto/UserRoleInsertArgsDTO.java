package hiram.module.system.pojo.dto;

import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HiramHe
 * @Date: 2020/10/2 17:15
 * @Description: ""
 */

@Data
@NoArgsConstructor
public class UserRoleInsertArgsDTO {

    /** 用户ID */

    private Long userId;

    /** 角色ID */
    private Long roleId;


    public UserRoleInsertArgsDTO(Long userId, Long roleId){
        this.userId = userId;
        this.roleId = roleId;
    }
}
