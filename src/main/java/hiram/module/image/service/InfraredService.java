package hiram.module.image.service;

import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.pojo.vo.InfraredListParam;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:17
 * @Description: ""
 */


public interface InfraredService {

    Infrared selectByInfraredId(Long infraredId);

    List<Infrared> list(InfraredListParam infraredListParam);

    Infrared insertOne(InfraredDTO infraredDTO) throws DataAccessException;
}
