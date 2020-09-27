package hiram.component.properties.token;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: HiramHe
 * @Date: 2020/5/15 19:28
 * @Description: ""
 */

// 只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenProperties {
    /*
    设置默认值
     */
    private String header = "Authorization";
    private String secret = "hiram.he@qq.com";
    //分钟
    private long expireTime = 30;
}
