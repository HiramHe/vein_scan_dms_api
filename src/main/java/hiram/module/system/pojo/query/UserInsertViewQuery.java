package hiram.module.system.pojo.query;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/7/17 10:51
 * @Description: ""
 */

@Data
@ApiModel
public class UserInsertViewQuery {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    private String nickname;

    private String realName;

    @Size(min = 0,max = 1,message = "长度不能大于1")
    private String sex;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @ApiModelProperty("yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Email
    private String email;

    @Size(min = 0,max = 11,message = "长度不能大于11")
    private String phoneNumber;

    private String remark;

    private Boolean enabled = true;


}
