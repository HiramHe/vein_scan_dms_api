package hiram.module.image.pojo.po;

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
    private Long descriptionXCoordinate;
    private Long descriptionYCoordinate;
    private Long bUltrasoundXCoordinate;
    private Long bUltrasoundYCoordinate;
    private Long bUltrasoundId;

}
