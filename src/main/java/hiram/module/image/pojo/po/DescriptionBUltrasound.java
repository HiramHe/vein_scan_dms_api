package hiram.module.image.pojo.po;

import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/10/4 18:49
 * @Description: ""
 */

@Data
public class DescriptionBUltrasound {

    private Long id;

    private Long descriptionId;
    private Long bUltrasoundId;

    private Long bUltrasoundXCoordinate;
    private Long bUltrasoundYCoordinate;
}
