package hiram.module.image.service;

import hiram.module.image.pojo.query.InfraredServiceQuery;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.pojo.query.InfraredListViewQuery;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:17
 * @Description: ""
 */


public interface InfraredService {

    Infrared selectByInfraredId(Long infraredId);

    List<Infrared> list(InfraredListViewQuery infraredListViewQuery);

    Infrared insertOne(InfraredServiceQuery infraredServiceQuery) throws DataAccessException;
}
