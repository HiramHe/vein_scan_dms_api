package hiram.module.image.service;

import hiram.module.image.pojo.query.BUltrasoundServiceQuery;
import hiram.module.image.pojo.po.BUltrasound;
import org.springframework.dao.DataAccessException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:18
 * @Description: ""
 */

public interface BUltrasoundService {

    BUltrasound insertOne(BUltrasoundServiceQuery bUltrasoundServiceQuery) throws DataAccessException;
}
