package hiram.module.patient.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import hiram.component.common.pojo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 12:28
 * @Description: ""
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends BaseEntity {

    @TableId(value = "patient_id")
    private Long patientId;
    private String patientName;
    private String sex;
}
