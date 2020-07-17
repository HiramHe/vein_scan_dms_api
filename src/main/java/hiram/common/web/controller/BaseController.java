package hiram.common.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import hiram.common.web.domain.vo.TableData;
import hiram.common.web.page.PageDomain;
import hiram.common.web.page.TableSupport;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 20:48
 * @Description: ""
 */

/*
web层通用数据处理
 */

public class BaseController {
    /*
    可被子类继承
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 设置请求分页数据
     */
    protected void startPage(){
        PageDomain pageDomain = TableSupport.getPageDomain();

        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();

        if (null !=pageNum && null !=pageSize){
            String orderBy = pageDomain.getOrderBy();
            PageHelper.startPage(pageNum,pageSize,orderBy);
        }
    }

    /**
     * 响应分页数据
     */
    protected TableData getTableData(List<?> list){
        TableData tableData = new TableData();

        tableData.setTotal(new PageInfo<>(list).getTotal());
        tableData.setResults(list);

        return tableData;
    }
}
