package hiram.module.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 21:57
 * @Description: ""
 */

/**
 * 自定义异常类，继承抽象类AuthenticationException，
 * 调用 AuthenticationEntryPoint的commence方法时，new 一
 * 个AuthenticationException对象，传进commence。
 */

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
