package hiram.module.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (SysBackendApi)表实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysBackendApi extends Model<SysBackendApi> {

    @TableId(type = IdType.NONE)
    private Long apiId;

    /**
     * 哪一个Controller
     */
    private String apiName;

    /**
     * API请求路径
     */
    private String url;

    /**
     * HTTP请求方法
     */
    private String method;

    private Integer sort;

    private String description;

}