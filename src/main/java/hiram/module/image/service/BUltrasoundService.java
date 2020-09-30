package hiram.module.image.service;

import hiram.module.image.pojo.dto.BUltrasoundDTO;
import hiram.module.image.pojo.entity.BUltrasound;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:18
 * @Description: ""
 */

public interface BUltrasoundService {

    BUltrasound insertOne(BUltrasoundDTO bUltrasoundDTO) throws DataAccessException;
}
