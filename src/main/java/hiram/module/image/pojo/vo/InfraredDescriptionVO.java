package hiram.module.image.pojo.vo;

import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.InfraredDescription;
import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/8/5 17:58
 * @Description: ""
 */

@Data
public class InfraredDescriptionVO extends InfraredDescription {

    private BUltrasound bUltrasound;
}
