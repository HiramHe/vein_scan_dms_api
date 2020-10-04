package hiram.module.image.pojo.po;

import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/8/5 17:05
 * @Description: ""
 */

@Data
public class BUltrasound extends BaseEntity {

    private Long bUltrasoundId;
    private String filename;
    private String path;
}
