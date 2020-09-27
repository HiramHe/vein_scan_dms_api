package hiram.component.security.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: HiramHe
 * @Date: 2020/5/7 17:15
 * @Description: ""
 */

/*
基于角色的访问控制
 */
public interface RBACService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
