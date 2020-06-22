package hiram.common.web;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 7:13
 * @Description: ""
 */

@JsonFormat
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(2000, "执行成功"),
    SUCCESS_AUTHENTICATE(2001,"身份认证成功"),
    SUCCESS_LOGOUT(2002,"退出成功"),

    /**
     * 失败
     */
    FAILED(4000, "操作失败"),
    FAILED_AUTHENTICATE(4001, "身份认证失败"),
    LOGIN_EXPIRED(4005,"登录已失效，请重新登录!")
    ;

    private final long code;
    private final String msg;

    private ResultCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
