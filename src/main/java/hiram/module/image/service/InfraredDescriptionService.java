package hiram.module.image.service;

import hiram.module.image.pojo.dto.InfraredDescriptionDTO;
import hiram.module.image.pojo.entity.InfraredDescription;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:19
 * @Description: ""
 */

public interface InfraredDescriptionService {

    InfraredDescription insertOne(InfraredDescriptionDTO infraredDescriptionDTO) throws DataAccessException;
}
