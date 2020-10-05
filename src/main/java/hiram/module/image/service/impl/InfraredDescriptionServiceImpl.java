package hiram.module.image.service.impl;

import hiram.module.image.mapper.InfraredDescriptionMapper;
import hiram.module.image.pojo.query.InfraredDescriptionServiceQuery;
import hiram.module.image.pojo.po.InfraredDescription;
import hiram.module.image.service.InfraredDescriptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
    public InfraredDescription insertOne(InfraredDescriptionServiceQuery infraredDescriptionServiceQuery) throws DataAccessException {

        InfraredDescription infraredDescription = new InfraredDescription();
        BeanUtils.copyProperties(infraredDescriptionServiceQuery,infraredDescription);

        int rt = infraredDescriptionMapper.insertOne(infraredDescription);

        return infraredDescription;
    }
}
