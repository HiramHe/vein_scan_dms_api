package hiram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 17:48
 * @Description: ""
 */

/*
@EnableConfigurationProperties(xxxProperties.class) :在spring启动时，
将自定义的xxxProperties类加入到ioc容器中。
项目中如果引入了spring security，默认开启了@EnableWebSecurity。
临时关闭spring security：
step1：启动类排除SecurityAutoConfiguration，关闭默认的spring security；
step2：自定义spring security配置类，注释掉 @EnableWebSecurity，关闭自定义的spring security。
*/

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
public class StartApplication {
    public static void main(String[] args) {

        SpringApplication.run(StartApplication.class, args);
    }
}
