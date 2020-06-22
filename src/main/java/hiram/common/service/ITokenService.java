package hiram.common.service;

import hiram.common.web.domain.dto.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 21:47
 * @Description: ""
 */

/*
由于token在多个模块中都需要被解析，所以抽取成公共服务。
 */

public interface ITokenService {

    String createToken(LoginUser loginUser);

    void refreshToken(LoginUser loginUser);

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    LoginUser getLoginUser(HttpServletRequest request) throws Exception;

    /**
     * 删除redis缓存的用户数据
     * @param key
     */
    void deleteLoginUser(String key);

}
