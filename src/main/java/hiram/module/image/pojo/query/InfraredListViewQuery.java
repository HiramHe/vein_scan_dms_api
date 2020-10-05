package hiram.module.image.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: HiramHe
 * @Date: 2020/8/5 16:23
 * @Description: ""
 */

@Data
public class InfraredListViewQuery {

    private String patientName;

    @ApiModelProperty(value = "起始扫描日期:yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp beginTime;

    @ApiModelProperty(value = "截止扫描日期:yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Timestamp endTime;
}
