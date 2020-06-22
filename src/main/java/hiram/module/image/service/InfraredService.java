package hiram.module.image.service;

import hiram.module.image.domain.Infrared;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:17
 * @Description: ""
 */


public interface InfraredService {

    Infrared selectByInfraredId(Long infraredId);

    List<Infrared> list();
}
