package hiram.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hiram.module.system.domain.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 14:42
 * @Description: ""
 */

@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {

    List<SysRole>  selectRolesByUsername(String username);
}
