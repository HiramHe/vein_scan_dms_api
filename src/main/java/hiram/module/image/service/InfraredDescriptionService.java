package hiram.module.image.service;

import hiram.module.image.pojo.dto.InfraredDescriptionDTO;
import hiram.module.image.pojo.po.InfraredDescription;
import org.springframework.dao.DataAccessException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:19
 * @Description: ""
 */

public interface InfraredDescriptionService {

    InfraredDescription insertOne(InfraredDescriptionDTO infraredDescriptionDTO) throws DataAccessException;
}
