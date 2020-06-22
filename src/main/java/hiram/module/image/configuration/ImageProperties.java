package hiram.module.image.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: HiramHe
 * @Date: 2020/5/20 20:13
 * @Description: ""
 */

@Component
@ConfigurationProperties(prefix = "image")
@Data
public class ImageProperties {

    private String infraredDirectory;
    private String ultrasoundDirectory;
}
