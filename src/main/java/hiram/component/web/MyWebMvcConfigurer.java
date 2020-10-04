package hiram.component.web;

import hiram.common.enums.ResourcePatternLocation;
import hiram.component.properties.file.ImageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: HiramHe
 * @Date: 2020/6/24 16:09
 * @Description: ""
 */

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    ImageProperties imageProperties;

    /**
     * 不要在yml配置文件中配置spring.mvc.static-path-pattern,
     * 否则所有的静态资源都需要走该配置的值才能访问到。
     * 而我希望的的是：swagger走：localhost:9090/swagger-ui.html，
     * 图片资源走：localhost:9090/picture/xxx.png。
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(ResourcePatternLocation.STATIC.getPathPattern())
                .addResourceLocations(ResourcePatternLocation.STATIC.getLocation());

        registry.addResourceHandler(ResourcePatternLocation.IMAGE.getPathPattern())
                .addResourceLocations("file:" + imageProperties.getInfraredDirectory())
                .addResourceLocations("file:" + imageProperties.getUltrasoundDirectory())
        ;

        /*
        swagger静态资源映射
         */
        registry.addResourceHandler(ResourcePatternLocation.SWAGGERUI.getPathPattern())
                .addResourceLocations(ResourcePatternLocation.SWAGGERUI.getLocation())
        ;
        registry.addResourceHandler(ResourcePatternLocation.WEBJARS.getPathPattern())
                .addResourceLocations(ResourcePatternLocation.WEBJARS.getLocation())
        ;

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT","PATCH")
//                .maxAge(3600 * 24)
        ;
    }
}
