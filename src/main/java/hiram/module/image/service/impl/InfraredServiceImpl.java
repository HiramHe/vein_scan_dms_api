package hiram.module.image.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.pojo.vo.InfraredListParam;
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
    public List<Infrared> list(InfraredListParam infraredListParam) {
        return infraredMapper.selectInfraredList(infraredListParam);
    }

    @Override
    public Infrared insertOne(InfraredDTO infraredDTO) throws DataAccessException {

        Infrared infrared = new Infrared();
        BeanUtils.copyProperties(infraredDTO,infrared);

        int rt = 0;
        rt = infraredMapper.insertOne(infrared);

        if (logger.isDebugEnabled()){
            logger.debug("rt="+rt);
        }

        return infrared;
    }
}
