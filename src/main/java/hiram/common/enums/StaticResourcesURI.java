package hiram.common.enums;

/**
 * @Author: HiramHe
 * @Date: 2020/6/24 16:56
 * @Description: ""
 */

public enum StaticResourcesURI {

    PICTURE_GENERAL("picture_general","/picture/xxx.png(.jpg)");

    private final String name;
    private final String uri;


    StaticResourcesURI(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName(){
        return this.name;
    }

    public String getUri(){
        return this.uri;
    }
}
