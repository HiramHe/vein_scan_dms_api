package hiram.module.image.service;

import hiram.module.image.pojo.query.InfraredDescriptionServiceQuery;
import hiram.module.image.pojo.po.InfraredDescription;
import org.springframework.dao.DataAccessException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:19
 * @Description: ""
 */

public interface InfraredDescriptionService {

    InfraredDescription insertOne(InfraredDescriptionServiceQuery infraredDescriptionServiceQuery) throws DataAccessException;
}
