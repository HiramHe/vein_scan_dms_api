package hiram.common.enums;

/**
 * @Author: HiramHe
 * @Date: 2020/6/24 16:56
 * @Description: ""
 */

public enum ResourcePatternLocation {

    IMAGE("images","/images/**",null),
    STATIC("static","/static/","classpath:/static/"),

    SWAGGERUI("swagger-ui","/swagger-ui.html","classpath:/META-INF/resources/"),
    WEBJARS("webjars","/webjars/**","classpath:/META-INF/resources/webjars/")
    ;

    private final String name;
    private final String pathPattern;
    private final String location;

    ResourcePatternLocation(String name, String pathPattern, String location) {
        this.name = name;
        this.pathPattern = pathPattern;
        this.location = location;
    }

    public String getName(){
        return this.name;
    }

    public String getPathPattern(){
        return this.pathPattern;
    }

    public String getLocation(){
        return this.location;
    }
}
