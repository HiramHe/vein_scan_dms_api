package hiram.module.image.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.module.image.pojo.query.InfraredServiceQuery;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.pojo.query.InfraredListViewQuery;
import hiram.module.image.mapper.InfraredMapper;
import hiram.module.image.service.InfraredService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:19
 * @Description: ""
 */

@Service
public class InfraredServiceImpl implements InfraredService {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    InfraredMapper infraredMapper;

    @Override
    public Infrared selectByInfraredId(Long infraredId) {
        QueryWrapper<Infrared> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("infrared_id",infraredId);

        return infraredMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Infrared> list(InfraredListViewQuery infraredListViewQuery) {
        return infraredMapper.selectInfraredList(infraredListViewQuery);
    }

    @Override
    public Infrared insertOne(InfraredServiceQuery infraredServiceQuery) throws DataAccessException {

        Infrared infrared = new Infrared();
        BeanUtils.copyProperties(infraredServiceQuery,infrared);

        int rt = 0;
        rt = infraredMapper.insertOne(infrared);

        if (logger.isDebugEnabled()){
            logger.debug("rt="+rt);
        }

        return infrared;
    }
}
