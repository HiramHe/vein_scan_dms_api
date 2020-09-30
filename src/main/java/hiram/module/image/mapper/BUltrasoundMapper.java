package hiram.module.image.mapper;

import hiram.module.image.pojo.entity.BUltrasound;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 17:50
 * @Description: ""
 */

@Mapper
public interface BUltrasoundMapper {
    int insertOne(BUltrasound bUltrasound) throws DataAccessException;
}
