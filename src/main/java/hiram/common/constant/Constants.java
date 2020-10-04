package hiram.common.constant;

/**
 * @Author: HiramHe
 * @Date: 2020/5/7 15:11
 * @Description: ""
 */
public class Constants {

    public static final long jwtExpirationMillis = 1000*60*60*2;

    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";

    public static Boolean UNIQUE = true;

    public static Boolean NOT_UNIQUE = false;
}
