package hiram.component.common.pojo.vo;

import hiram.common.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HiramHe
 * @Date: 2020/5/4 7:07
 * @Description: ""
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "接口统一返回对象")
public class ResultObject<T> {

    @ApiModelProperty(value = "http响应码")
    private int httpCode;
    private static final int HTTPCODE_DEFAULT = 200;

    @ApiModelProperty("接口响应码")
    private long code;

    @ApiModelProperty("接口响应信息")
    private String msg;

    @ApiModelProperty("接口响应数据")
    private T data;

    public static <T> ResultObject<T> getResultObjectWithProperty(int httpCode,long code, String msg,T data) {
        ResultObject<T> resultObject = new ResultObject<>();

        resultObject.setHttpCode(httpCode);
        resultObject.setCode(code);
        resultObject.setMsg(msg);
        resultObject.setData(data);

        return resultObject;
    }

    public static <T> ResultObject<T> getResultObjectWithResultCode(int httpCode, ResultCode resultCode, T data) {
        return getResultObjectWithProperty(httpCode,resultCode.getCode(), resultCode.getMsg(),data);
    }

    public static <T> ResultObject<T> success(int httpCode,ResultCode resultCode,T data) {
        return getResultObjectWithResultCode(httpCode,resultCode,data);
    }

    public static <T> ResultObject<T> success(ResultCode resultCode,T data) {
        return getResultObjectWithResultCode(HTTPCODE_DEFAULT,resultCode,data);
    }

    public static <T> ResultObject<T> success(ResultCode resultCode) {
        return getResultObjectWithResultCode(HTTPCODE_DEFAULT,resultCode,null);
    }


    public static <T> ResultObject<T> failed(int httpCode,ResultCode resultCode) {
        return getResultObjectWithResultCode(httpCode,resultCode,null);
    }

    public static <T> ResultObject<T> failed(ResultCode resultCode) {
        return getResultObjectWithResultCode(HTTPCODE_DEFAULT,resultCode,null);
    }

    public static <T> ResultObject<T> failed(int httpCode,long code, String msg,T data) {
        return getResultObjectWithProperty(httpCode,code,msg,data);
    }

}
