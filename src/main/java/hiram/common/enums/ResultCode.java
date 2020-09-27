package hiram.common.enums;

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
    SUCCESS_AUTHENTICATE(2011,"身份认证成功"),
    SUCCESS_LOGOUT(2021,"退出成功"),

    /**
     * 失败
     */
    FAILED(4000, "操作失败"),
    COLLECTION_NULL(4002,"集合不得为空"),
    FAILED_AUTHENTICATE(4011, "身份认证失败"),
    LOGIN_EXPIRED(4012,"登录已失效，请重新登录"),
    USER_EXIST(4014,"用户已存在"),
    USERNAME_PASSWORD_NULL(4016,"用户名和密码不得为空"),
    PHONENUMBER_NOT_UNIQUE(4017,"手机号已存在"),
    EMAIL_NOT_UNIQUE(4018,"邮箱账号已存在"),
    TOKEN_UNTRUSTED(4021,"不受信的token凭证"),
    TOKEN_NULL(4022,"token不能为空"),
    USER_NOT_EXIST(4031,"用户不存在"),
    OLDPASSWORD_ERROR(4040,"旧密码错误"),
    NEWPASSWORD_SAME_ERROR(4042,"新密码与旧密码不能相同"),
    RESETPASSWORD_ERROR(4044,"修改密码异常，请联系管理员"),

    FILE_UPLOAD_INFRARED_EMPTY(4111,"红外图像不得为空"),
    FILE_FORMAT_NOT_SUPPORT(4112,"文件格式不支持"),
    FILE_SIZE_TOO_LARGE(4113,"文件大小过大")
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
