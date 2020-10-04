package hiram.module.system.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: HiramHe
 * @Date: 2020/7/16 21:14
 * @Description: ""
 */

@Data
public class UserUpdateArgsVO {

    @NotNull(message = "useId不能为空")
    private Long userId;

    private String username;

    private String nickname;

    private String realName;

    @Size(max = 1)
    private String sex;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Email(message = "邮箱格式不正确")
    private String email;

    @Size(min = 0,max = 11,message = "手机号码长度不能超过11个字符")
    private String phoneNumber;

    private String avatar;

    private String remark;

    private Boolean enabled;

    /** 角色组 */
    private Long[] roleIds;
}