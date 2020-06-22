package hiram.common.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 7:07
 * @Description: ""
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "接口统一返回对象")
public class ResultObject<T> extends HashMap<String,Object> {

    @ApiModelProperty(value = "http响应码")
    private int httpCode;
    private static final int DEFAULT_HTTPCODE = 200;

    @ApiModelProperty("接口响应码")
    private long code;

    @ApiModelProperty("接口响应信息")
    private String msg;

    @ApiModelProperty("接口响应数据")
    private T data;


    public static <T> ResultObject<T> restResult(int httpCode, ResultCode resultCode, T data) {
        return restResult(httpCode,resultCode.getCode(), resultCode.getMsg(),data);
    }

    public static <T> ResultObject<T> restResult(int httpCode,long code, String msg,T data) {
        ResultObject<T> resultObject = new ResultObject<>();

        resultObject.setHttpCode(httpCode);
        resultObject.setCode(code);
        resultObject.setMsg(msg);
        resultObject.setData(data);

        return resultObject;
    }


    public static <T> ResultObject<T> success(int httpCode,ResultCode resultCode,T data) {
        return restResult(httpCode,resultCode,data);
    }

    public static <T> ResultObject<T> success(ResultCode resultCode,T data) {
        return restResult(DEFAULT_HTTPCODE,resultCode,data);
    }


    public static <T> ResultObject<T> failed(int httpCode,ResultCode resultCode) {
        return restResult(httpCode,resultCode,null);
    }

    public static <T> ResultObject<T> failed(ResultCode resultCode) {
        return restResult(DEFAULT_HTTPCODE,resultCode,null);
    }

    public static <T> ResultObject<T> failed(int httpCode,long code, String msg,T data) {
        return restResult(httpCode,code,msg,data);
    }

}
