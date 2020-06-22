package hiram.common.web.domain.dto;

import lombok.*;

import javax.validation.constraints.Null;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 20:25
 * @Description: "公共的DTO"
 */

@NoArgsConstructor
@AllArgsConstructor
@Null
@Getter
@Setter
@ToString
public class BaseDTO implements Serializable {

    private Boolean deleted;

    private Timestamp createTime;

    private Timestamp updateTime;
}
