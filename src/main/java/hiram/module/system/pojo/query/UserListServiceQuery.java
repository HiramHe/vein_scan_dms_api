package hiram.module.system.pojo.query;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/7/17 10:51
 * @Description: ""
 */

/*
ac = accept
 */

@Data
@ApiModel
public class UserListServiceQuery {

    private String username;
    private String phoneNumber;
    private Timestamp beginTime;
    private Timestamp endTime;
}
