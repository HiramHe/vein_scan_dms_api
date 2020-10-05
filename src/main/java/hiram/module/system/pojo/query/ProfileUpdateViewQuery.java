package hiram.module.system.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @Author: HiramHe
 * @Date: 2020/10/3 10:00
 * @Description: ""
 */

@Data
public class ProfileUpdateViewQuery {

    private String username;

    private String nickname;

    private String realName;

    @Size(max = 1)
    private String sex;

//    @ApiModelProperty("yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiParam("yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Size(min = 0,max = 11,message = "手机号码长度不能超过11个字符")
    private String phoneNumber;

    private String remark;

}
