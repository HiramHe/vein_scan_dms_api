package hiram.module.image.service;

import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.pojo.vo.InfraredListParam;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:17
 * @Description: ""
 */


public interface InfraredService {

    Infrared selectByInfraredId(Long infraredId);

    List<Infrared> list(InfraredListParam infraredListParam);
}
