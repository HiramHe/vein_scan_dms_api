package hiram.component.exception;

/**
 * @Author: HiramHe
 * @Date: 2020/6/26 15:35
 * @Description: ""
 */

public class TokenException extends RuntimeException {

    public TokenException(){}

    public TokenException(String msg){
        super(msg);
    }
}
