package hiram.module.image.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.pojo.vo.InfraredListParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:20
 * @Description: ""
 */

@Mapper
public interface InfraredMapper extends BaseMapper<Infrared> {

    List<Infrared> selectInfraredList(InfraredListParam infraredListParam);

    int insertOne(Infrared infrared) throws DataAccessException;
}
