package hiram.module.system.pojo.entity;

import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: HiramHe
 * @Date: 2020/4/24 10:35
 * @Description: ""
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRole extends BaseEntity implements GrantedAuthority {

    /** 角色ID */
    private Long roleId;

    /** 角色名称 */
    private String roleNameEn;

    /** 角色名称 */
    private String roleNameZh;

    /** 角色状态 */
    private boolean enabled;

    private String remark;

    /*
    非表中字段。@TableField(exist = false)
    因为GrantedAuthority的缘故，此属性必须设置。
     */
    private String authority;


    @Override
    public String getAuthority() {
        return roleNameEn;
    }

//    @NotBlank(message = "角色名称不能为空")
//    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
//    public String getRoleNameZh()
//    {
//        return roleNameZh;
//    }
//
//    public void setRoleNameZh(String roleNameZh)
//    {
//        this.roleNameZh = roleNameZh;
//    }

}
