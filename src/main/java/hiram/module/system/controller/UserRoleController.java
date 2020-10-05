package hiram.module.system.controller;

import hiram.common.enums.ResultCode;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.module.system.pojo.query.UserRoleInsertServiceQuery;
import hiram.module.system.pojo.po.UserRole;
import hiram.module.system.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/10/2 17:08
 * @Description: ""
 */

@Api( tags = "用户角色管理接口")
@RestController
@RequestMapping(value = "/system/userRole")
public class UserRoleController {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "添加用户角色")
    @PutMapping("/add")
    public ResultObject<?> add(@RequestParam() Long userId,
                               @RequestParam() Long roleId){

        UserRoleInsertServiceQuery userRoleInsertServiceQuery = new UserRoleInsertServiceQuery(userId,roleId);
        UserRole userRole = null;

        try {
            userRole = userRoleService.insertUserRole(userRoleInsertServiceQuery);

        } catch (Exception e) {

            if (e instanceof DuplicateKeyException){
                return ResultObject.failed(ResultCode.RECORD_EXIST);
            }else {
                return ResultObject.failed(ResultCode.FAILED);
            }
        }

        Map<String,UserRole> data = new HashMap<>();
        data.put("userRole",userRole);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    @ApiOperation("删除用户角色")
    @DeleteMapping("/delete")
    public ResultObject<?> delete(@RequestParam() Long userId,
                                 @RequestParam(required = false) Long roleId){

        Long rt = 0L;

        if (roleId == null){
            try {
                rt = userRoleService.deleteUserRoleByUserId(userId);
            } catch (Exception e) {
                return ResultObject.failed(ResultCode.FAILED);
            }
        }else {
            try {
                rt = userRoleService.deleteUserRoleByUserIdRoleId(userId, roleId);
            } catch (Exception e) {
                return ResultObject.failed(ResultCode.FAILED);
            }
        }

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",rt);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }
}
