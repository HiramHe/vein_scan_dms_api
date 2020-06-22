package hiram.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hiram.module.system.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:53
 * @Description: ""
 */

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUserList();
}
