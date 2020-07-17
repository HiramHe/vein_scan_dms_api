package hiram.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: HiramHe
 * @Date: 2020/6/24 16:09
 * @Description: ""
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    PictureProperties pictureProperties;

    /**
     * 不要在yml配置文件中配置spring.mvc.static-path-pattern,
     * 否则所有的静态资源都需要走该配置的值才能访问到。
     * 而我希望的的是：swagger走：localhost:9090/swagger-ui.html，
     * 图片资源走：localhost:9090/picture/xxx.png。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");

        registry.addResourceHandler("/picture/**")
                .addResourceLocations("file:" + pictureProperties.getInfraredDirectory())
                .addResourceLocations("file:" + pictureProperties.getUltrasoundDirectory())
        ;

        /*
        swagger静态资源映射
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")
        ;
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
        ;

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("GET","POST","DELETE","PUT","PATCH")
                .maxAge(3600 * 24);
    }
}
