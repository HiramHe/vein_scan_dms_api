package hiram.module.image.pojo.query;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 16:28
 * @Description: ""
 */

@Data
public class InfraredServiceQuery {

    private String perspective;
    private String filename;
    private String path;
    private LocalDateTime scanTime;
    private Long patientId;
    private Long userId;
}
