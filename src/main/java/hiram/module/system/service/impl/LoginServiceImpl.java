package hiram.module.system.service.impl;

import hiram.component.common.service.ITokenService;
import hiram.component.common.pojo.vo.LoginUser;
import hiram.component.exception.UserException;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.service.IUserService;
import hiram.module.system.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: HiramHe
 * @Date: 2020/6/26 14:21
 * @Description: ""
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    IUserService iUserService;

    @Autowired
    ITokenService iTokenService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public String login(String username, String password) {
        SysUser dbUser = iUserService.selectUserByUsername(username);

        if(dbUser == null){
            throw new UserException();
        }

        boolean matches = encoder.matches(password, dbUser.getPassword());

        String token = null;
        LoginUser loginUser;
        if(matches){
            loginUser = new LoginUser();
            loginUser.setUser(dbUser);
            token = iTokenService.createToken(loginUser);
        }

        return token;
    }
}
