package hiram.common.exception;

import org.springframework.util.StringUtils;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 20:48
 * @Description: "实体已经存在异常"
 */

public class EntityExistException extends RuntimeException{

    public EntityExistException(Class clazz, String field, String val){
        super(EntityExistException.generateMessage(clazz.getSimpleName(),field,val));
    }

    private static String generateMessage(String entity, String field, String val){
        return StringUtils.capitalize(entity) +
                " " + "with" + field +
                " " + val +
                " " + "exists.";
    }
}
