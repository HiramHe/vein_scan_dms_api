package hiram.module.image.service.impl;

import hiram.module.image.mapper.InfraredDescriptionMapper;
import hiram.module.image.pojo.dto.InfraredDescriptionDTO;
import hiram.module.image.pojo.entity.InfraredDescription;
import hiram.module.image.service.InfraredDescriptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 18:14
 * @Description: ""
 */

@Service
public class InfraredDescriptionServiceImpl implements InfraredDescriptionService {

    @Autowired
    InfraredDescriptionMapper infraredDescriptionMapper;

    @Override
    public InfraredDescription insertOne(InfraredDescriptionDTO infraredDescriptionDTO) throws DataAccessException {

        InfraredDescription infraredDescription = new InfraredDescription();
        BeanUtils.copyProperties(infraredDescriptionDTO,infraredDescription);

        int rt = infraredDescriptionMapper.insertOne(infraredDescription);

        return infraredDescription;
    }
}
