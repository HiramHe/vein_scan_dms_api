package hiram.module.image.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import hiram.module.image.domain.Infrared;
import hiram.module.image.mapper.InfraredMapper;
import hiram.module.image.service.InfraredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/21 10:19
 * @Description: ""
 */

@Service
public class InfraredServiceImpl implements InfraredService {

    @Autowired
    InfraredMapper infraredMapper;

    @Override
    public Infrared selectByInfraredId(Long infraredId) {
        QueryWrapper<Infrared> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("infrared_id",infraredId);

        return infraredMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Infrared> list() {
        return infraredMapper.selectList(null);
    }
}
