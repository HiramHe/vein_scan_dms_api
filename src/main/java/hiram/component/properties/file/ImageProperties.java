package hiram.component.properties.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: HiramHe
 * @Date: 2020/6/24 16:45
 * @Description: ""
 */

@Component
@ConfigurationProperties(prefix = "image")
@Data
public class ImageProperties {

    private String infraredDirectory = "";
    private String ultrasoundDirectory = "";
}
