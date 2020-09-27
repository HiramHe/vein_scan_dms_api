package hiram.module.system.pojo.entity;

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

    /**
     *  路由地址
     */
    private String path;

    private String component;

    private String icon;

    private boolean enabled = true;
}
