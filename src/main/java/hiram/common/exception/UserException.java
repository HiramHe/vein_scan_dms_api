package hiram.common.exception;

/**
 * @Author: HiramHe
 * @Date: 2020/6/26 15:58
 * @Description: ""
 */

public class UserException extends RuntimeException {

    public UserException(){};

    public UserException(String msg){
        super(msg);
    }
}
