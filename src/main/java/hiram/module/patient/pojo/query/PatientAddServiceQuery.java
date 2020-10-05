package hiram.module.patient.pojo.query;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @Author: HiramHe
 * @Date: 2020/10/5 14:51
 * @Description: ""
 */

@Data
public class PatientAddServiceQuery {

    private String patientName;
    private String sex;
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    private Boolean enabled;
    private String remark;
    private String addId;
    private String addOrient;

}
