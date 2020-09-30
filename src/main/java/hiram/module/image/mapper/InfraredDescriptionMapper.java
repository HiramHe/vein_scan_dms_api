package hiram.module.image.mapper;

import hiram.module.image.pojo.entity.InfraredDescription;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 18:18
 * @Description: ""
 */

@Mapper
public interface InfraredDescriptionMapper {
    int insertOne(InfraredDescription infraredDescription) throws DataAccessException;
}
