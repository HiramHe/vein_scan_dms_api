package hiram.component.security.service.impl;

import hiram.component.security.service.RBACService;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: HiramHe
 * @Date: 2020/5/7 17:17
 * @Description: ""
 */


public class RBACServiceImpl implements RBACService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        return false;
    }
}
