package hiram.module.image.mapper;

import hiram.module.image.pojo.po.BUltrasound;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 17:50
 * @Description: ""
 */

@Mapper
public interface BUltrasoundMapper {
    int insertOne(BUltrasound bUltrasound) throws DataAccessException;
}
