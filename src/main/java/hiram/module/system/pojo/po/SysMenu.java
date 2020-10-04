package hiram.module.system.pojo.po;

import hiram.component.common.pojo.entity.BaseEntity;

/**
 * @Author: HiramHe
 * @Date: 2020/4/24 9:56
 * @Description: ""
 */


public class SysMenu extends BaseEntity {

    private Long menuId;
    private String menuNameZh;
    private String menuNameEn;
    private Long parentId;
    private Boolean visible;
    private Boolean requireAuth;
    private String icon;
    /**
     *  路由地址
     */
    private String path;
    private String component;
    private Boolean hidden;
    private String redirect;
    private Boolean keepAlive;
    private String remark;
    private boolean enabled = true;
}
