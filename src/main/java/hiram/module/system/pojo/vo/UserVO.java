package hiram.module.system.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: HiramHe
 * @Date: 2020/10/3 9:38
 * @Description: ""
 */

@Data
public class UserVO {

    private Long userId;

    private String username;

    private String nickname;

    private String realName;

    private String sex;

    private LocalDate birthday;

    private String email;

    private String phoneNumber;

    private String avatar;

    private String remark;

    private Boolean enabled;
}
