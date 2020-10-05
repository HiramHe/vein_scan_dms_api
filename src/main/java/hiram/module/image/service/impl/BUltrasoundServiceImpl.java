package hiram.module.image.service.impl;

import hiram.module.image.mapper.BUltrasoundMapper;
import hiram.module.image.pojo.query.BUltrasoundServiceQuery;
import hiram.module.image.pojo.po.BUltrasound;
import hiram.module.image.service.BUltrasoundService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 17:49
 * @Description: ""
 */

@Service
public class BUltrasoundServiceImpl implements BUltrasoundService {

    @Autowired
    BUltrasoundMapper bUltrasoundMapper;

    @Override
    public BUltrasound insertOne(BUltrasoundServiceQuery bUltrasoundServiceQuery) throws DataAccessException {

        BUltrasound bUltrasound = new BUltrasound();
        BeanUtils.copyProperties(bUltrasoundServiceQuery,bUltrasound);

        int rt = bUltrasoundMapper.insertOne(bUltrasound);

        return bUltrasound;
    }
}
