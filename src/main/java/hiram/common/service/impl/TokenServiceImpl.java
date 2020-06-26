package hiram.common.service.impl;

import hiram.common.jwt.TokenProperties;
import hiram.common.constant.Constants;
import hiram.common.exception.TokenException;
import hiram.common.service.ITokenService;
import hiram.common.jwt.JwtUtils;
import hiram.common.utils.IdUtils;
import hiram.common.web.domain.dto.LoginUser;
import hiram.common.redis.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 21:50
 * @Description: ""
 */

/*
使用redis实现token的时效性，不使用jwt本身的时效性
 */

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenProperties tokenProperties;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    @Override
    public String createToken(LoginUser loginUser) {

        Claims claims = new DefaultClaims();
        String uuid = IdUtils.randomUUID();
        claims.put(Constants.LOGIN_USER_KEY,uuid);

        loginUser.setUuid(uuid);

        String token = JwtUtils.generateToken(claims,tokenProperties.getSecret());

        this.refreshToken(loginUser);

        return token;
    }

    @Override
    public void refreshToken(LoginUser loginUser) {

        long currentTimeMillis = System.currentTimeMillis();

        loginUser.setLoginTime(currentTimeMillis);
        long expireTime = tokenProperties.getExpireTime();
        String uuid = loginUser.getUuid();
        // 将loginUser缓存起来
        if(expireTime != -1){
            loginUser.setExpireTime( currentTimeMillis + tokenProperties.getExpireTime()*MILLIS_MINUTE );
            redisCache.setCacheObject(uuid,loginUser,tokenProperties.getExpireTime(), TimeUnit.MINUTES);
        }else{
            // expireTime == -1:缓存不失效
            loginUser.setExpireTime(expireTime);
            redisCache.setCacheObject(uuid,loginUser);
        }

    }

    public LoginUser getLoginUser(HttpServletRequest request) throws Exception {
        // 获取请求携带的令牌
        String token = getToken(request);

        if(StringUtils.isEmpty(token)){
            throw new TokenException("token is null");
        }

        Claims claims = JwtUtils.parseToken(token,tokenProperties.getSecret());
        // 解析对应的权限以及用户信息
        String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);

        return redisCache.getCacheObject(uuid);

    }

    @Override
    public void deleteLoginUser(String key) {
        if(StringUtils.isNotEmpty(key)){
            redisCache.deleteObject(key);
        }
    }

    /**
     * 获取请求中的token
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(tokenProperties.getHeader());
        if ( !StringUtils.isEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            /*
            trim()：去掉空格
            */
            token = token.replace(Constants.TOKEN_PREFIX, "").trim();
        }
        return token;
    }
}
