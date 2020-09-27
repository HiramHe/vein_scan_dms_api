package hiram.component.common.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/22 11:48
 * @Description: ""
 */

@Data
public class TableData {

    /*总记录数*/
    private long total;

    /*列表数据*/
    private List<?> results;
}
