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
    private Long xCoordinate;
    private Long yCoordinate;
    private Long bUltrasoundId;

}
