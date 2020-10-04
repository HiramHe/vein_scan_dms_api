package hiram.module.image.pojo.dto;

import lombok.Data;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:31
 * @Description: ""
 */

@Data
public class InfraredDescriptionDTO {

    private Long infraredId;
    private String description;
    private String severityLevel;
    private Long descriptionXCoordinate;
    private Long descriptionYCoordinate;
    private Long bUltrasoundXCoordinate;
    private Long bUltrasoundYCoordinate;
    private Long bUltrasoundId;

}
