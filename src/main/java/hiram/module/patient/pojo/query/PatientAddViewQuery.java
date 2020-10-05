package hiram.module.patient.pojo.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @Author: HiramHe
 * @Date: 2020/10/5 14:38
 * @Description: ""
 */

/*
@NotNull：用在所有类型上面
NotEmpty：用在集合上面
NotBlank：用在string上面
 */

@Data
public class PatientAddViewQuery {

    @NotNull
    @NotBlank
    private String patientName;

    @Size(max = 1,message = "字符个数不得超过1")
    private String sex;

    @ApiModelProperty("yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Email
    private String email;

    private String phoneNumber;
    private Boolean enabled;
    private String remark;
    private String addId;
    private String addOrient;
}
