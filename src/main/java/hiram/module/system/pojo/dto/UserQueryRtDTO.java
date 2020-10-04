package hiram.module.system.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import hiram.component.common.pojo.entity.BaseEntity;
import hiram.module.system.pojo.po.SysRole;
import hiram.module.system.pojo.po.SysUser;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/7/17 10:51
 * @Description: ""
 */

/*
rt = return
 */

@Data
public class UserQueryRtDTO extends SysUser {

    private List<SysRole> roles;
}
