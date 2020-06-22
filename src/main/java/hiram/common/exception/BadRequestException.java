package hiram.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 20:43
 * @Description: ""
 */

@Getter
public class BadRequestException extends RuntimeException {

    private Integer status = BAD_REQUEST.value();

    private BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }

    private BadRequestException(String msg){
        super(msg);
    }
}
