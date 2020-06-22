package hiram.common.exception;

import org.springframework.util.StringUtils;

/**
 * @Author: HiramHe
 * @Date: 2020/4/23 20:56
 * @Description: "实体不存在"
 */

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String field, String val){
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(),field,val));
    }

    private static String generateMessage(String entity, String field, String val){
        return StringUtils.capitalize(entity) +
                " " + "with" + field + " " + val +
                " " + "does not exist.";
    }
}
