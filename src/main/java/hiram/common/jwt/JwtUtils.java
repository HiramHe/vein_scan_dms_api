package hiram.common.jwt;

import hiram.common.utils.JsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.util.Base64;
import java.util.Date;
import java.util.UUID;


/**
 * @Author: HiramHe
 * @Date: 2020/5/5 13:41
 * @Description: "jwt工具类"
 */

/*
 * JwtException继承RuntimeException，
 * 所以JwtException及其子类均为运行时异常。
 */
public class JwtUtils {

    //私钥
    private static final String DEFAULT_SECRET = "hiram";

    /**
     * 生成 token
     * @param claims
     * @return
     */
    public static String generateToken(Claims claims) {

        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, DEFAULT_SECRET)
                .compact();
    }

    public static String generateToken(Claims claims,String secret) {

        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private static String createJTI() {
        return new String(Base64.getEncoder().encode(UUID.randomUUID().toString().getBytes()));
    }

    /**
     * 从数据声明生成 token
     * @param object
     * @param millis
     * @return
     */
    public static String generateTokenExpireInMillis(Object object,long millis){

        Date issued_at = new Date();
        Date expirationTime = new Date(issued_at.getTime()+millis);

        Claims claims = new DefaultClaims();
        claims.setSubject(JsonUtils.toString(object));
        claims.setIssuedAt(issued_at);
        claims.setExpiration(expirationTime);
        claims.setId(createJTI());

        return generateToken(claims);
    }

    /**
     * 从数据声明生成 token
     * 不用使用Map形式的claims，那种设置的各种时间无效。
     * @param
     * @return
     */
    public static String generateTokenExpireInMinutes(Object object,long minutes) {

        return generateTokenExpireInMillis(object,1000*60*minutes);
    }

    /**
     * 从数据声明生成 token
     * @param
     * @return
     */
    public static String generateTokenExpireInSeconds(Object object,long seconds){

        return generateTokenExpireInMillis(object,1000*seconds);
    }

    /**
     * 解析token
     * @param
     * @param
     * @return
     */
    public static Jws<Claims> getJwsFromToken(String token) throws Exception {

        /**
         * 在解析时可能出现各种异常，
         * 比如token格式错误，token过期异常等，
         * 全部抛出去。
         */
        return Jwts
                .parser()
                .setSigningKey(DEFAULT_SECRET)
                .parseClaimsJws(token);

    }

    public static Claims parseToken(String token,String secret) throws Exception{
        return Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从token中获取body
     * @param
     * @param
     * @return
     */
    public static Claims getClaimsFromToken(String token) throws Exception {

        return getJwsFromToken(token).getBody();
    }

    /**
     * 获取token中的载荷信息，不包括object
     *
     * @param
     * @param
     * @return
     */
    public static <T> Payload<T> getPayloadFromToken(String token) throws Exception {

        Claims body = getClaimsFromToken(token);

        Payload<T> payload = new Payload<>();
        payload.setId(body.getId());
        payload.setExpiration(body.getExpiration());

        return payload;
    }

    /**
     * 获取token中的载荷信息，包括object
     * @param
     * @param
     * @param
     * @return
     */
    public static <T> Payload<T> getPayloadFromToken(String token,Class<T> tClass) throws Exception {

        Claims body = getClaimsFromToken(token);

        Payload<T> payload = new Payload<>();
        payload.setId(body.getId());
        payload.setObject(JsonUtils.toBean(body.getSubject(), tClass));
        payload.setExpiration(body.getExpiration());

        return payload;
    }

    /**
     * 刷新token
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token,long milliseconds) throws Exception {
        String refreshedToken;
        Date issued_at = new Date();
        Date expirationTime = new Date(issued_at.getTime()+milliseconds);

        Claims claims = getClaimsFromToken(token);
        claims.setIssuedAt(issued_at);
        claims.setExpiration(expirationTime);
        refreshedToken = generateToken(claims);

        return refreshedToken;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static boolean isTokenExpired(String token) throws Exception {

        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());

    }

}
