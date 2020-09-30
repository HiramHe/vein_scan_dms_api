package hiram.component.properties.file;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
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
public class ImageProperties implements InitializingBean {

    private String infraredDirectoryOnWin = "G:/test/infrared";
    private String ultrasoundDirectoryOnWin = "G:/test/ultrasound";

    private String infraredDirectoryOnLinux = "/tmp/infrared";
    private String ultrasoundDirectoryOnLinux = "/tmp/ultrasound";

    private String infraredDirectory = "";
    private String ultrasoundDirectory = "";

    //重构路径
    private void setDirectory(){

        boolean isWin = System.getProperty("os.name").toLowerCase().contains("win");
        boolean isLinux = System.getProperty("os.name").toLowerCase().contains("linux");

        infraredDirectoryOnWin = infraredDirectoryOnWin.endsWith("/") ?
                infraredDirectoryOnWin : infraredDirectoryOnWin + "/";

        ultrasoundDirectoryOnWin = ultrasoundDirectoryOnWin.endsWith("/") ?
                ultrasoundDirectoryOnWin : ultrasoundDirectoryOnWin + "/";

        infraredDirectoryOnLinux = infraredDirectoryOnLinux.endsWith("/") ?
                infraredDirectoryOnLinux : infraredDirectoryOnLinux + "/";

        ultrasoundDirectoryOnLinux = ultrasoundDirectoryOnLinux.endsWith("/") ?
                ultrasoundDirectoryOnLinux : ultrasoundDirectoryOnLinux + "/";

        if (isWin){
            infraredDirectory = infraredDirectoryOnWin;
            ultrasoundDirectory = ultrasoundDirectoryOnWin;
        }else if (isLinux){
            infraredDirectory = infraredDirectoryOnLinux;
            ultrasoundDirectory = ultrasoundDirectoryOnLinux;
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setDirectory();
    }
}
