package hiram.module.image.pojo.entity;

import hiram.component.common.pojo.entity.BaseEntity;
import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/8/5 16:55
 * @Description: ""
 */

@Data
public class InfraredDescription extends BaseEntity {

    private Long descriptionId;
    private Long infraredId;
    private String description;
    private String severityLevel;
    private Long xCoordinate;
    private Long yCoordinate;
    private Long bUltrasoundId;

}
