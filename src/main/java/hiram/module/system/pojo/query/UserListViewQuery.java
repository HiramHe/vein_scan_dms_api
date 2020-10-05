package hiram.module.system.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: HiramHe
 * @Date: 2020/7/16 19:43
 * @Description: ""
 */

@Data
@ApiModel
public class UserListViewQuery {

    private String username;

    private String phoneNumber;

    @ApiModelProperty(value = "起始创建日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate beginTime;

    @ApiModelProperty(value = "截止创建日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate endTime;
}
