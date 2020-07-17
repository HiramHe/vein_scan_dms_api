package hiram.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hiram.common.web.domain.entity.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: HiramHe
 * @Date: 2020/4/29 16:48
 * @Description: ""
 */
public class JsonUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);


    public static void writeObject(HttpServletRequest request,
                          HttpServletResponse response,
                          ResultObject resultObject) throws IOException {

        //很重要，否则前端获取不到JSON
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(resultObject.getHttpCode());
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Method","POST,GET");

        //输出json
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(resultObject));

        writer.flush();
        writer.close();
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    public static <T> T toBean(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (IOException e) {
            logger.error("json解析出错：" + json, e);
            return null;
        }
    }
}
