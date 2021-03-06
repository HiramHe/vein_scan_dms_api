package hiram.module.patient.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import hiram.component.common.pojo.entity.BaseEntity;
import lombok.*;

import java.time.LocalDate;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 12:28
 * @Description: ""
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Patient extends BaseEntity {

    private Long patientId;
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
