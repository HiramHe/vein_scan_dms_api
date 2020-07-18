package hiram.module.system.controller;

import hiram.common.enums.ResultCode;
import hiram.common.utils.MyStringUtils;
import hiram.common.web.domain.entity.ResultObject;
import hiram.common.web.controller.BaseController;
import hiram.common.web.domain.vo.TableData;
import hiram.module.system.domain.entity.SysUser;
import hiram.module.system.domain.vo.UserInsertVO;
import hiram.module.system.domain.vo.UserListParam;
import hiram.module.system.domain.vo.UserUpdateParam;
import hiram.module.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 20:20
 * @Description: ""
 */

@Api( tags = "用户管理接口")
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    private Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "查询用户列表")
    @PostMapping("/list")
    public ResultObject<?> selectUserList(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false) int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestBody(required = false) UserListParam userListParam){

        if(logger.isDebugEnabled()){
            if(userListParam.getBeginTime() != null){
                System.out.println("beginTime:"+userListParam.getBeginTime());
                System.out.println("Type:"+userListParam.getBeginTime().getClass());
            }
        }

        this.startPage();
        List<SysUser> sysUsers = iUserService.selectUserList(userListParam);

        TableData tableData = this.getTableData(sysUsers);

        return ResultObject.success(ResultCode.SUCCESS,tableData);
    }

    @ApiOperation(value = "添加系统用户")
    @PutMapping("/addUser")
    public ResultObject<?> addUser(
            @RequestBody UserInsertVO userInsertVO){

        ////检查用户名和密码是否为空
        if(MyStringUtils.isEmpty(userInsertVO.getUsername())  || MyStringUtils.isEmpty(userInsertVO.getPassword())){
            return ResultObject.failed(ResultCode.USERNAME_PASSWORD_NULL);
        }

        SysUser user = new SysUser();
        BeanUtils.copyProperties(userInsertVO,user);

        //检查用户名是否已存在
        boolean userNameExist = iUserService.checkUserNameExist(user.getUsername());
        if(userNameExist){
            return ResultObject.failed(ResultCode.USER_EXIST);
        }

        //检查手机号是否已存在
        if(!MyStringUtils.isEmpty(user.getPhoneNumber()) && !iUserService.checkPhoneUnique(user)){
            return ResultObject.failed(ResultCode.PHONENUMBER_NOT_UNIQUE);
        }

        //检查邮箱账号是否已存在
        if(!MyStringUtils.isEmpty(user.getEmail()) && !iUserService.checkEmailUnique(user)){
            return ResultObject.failed(ResultCode.EMAIL_NOT_UNIQUE);
        }

        Long affectRows = iUserService.insertUser(userInsertVO);
        Map<String,Long> data = new HashMap<>();
        data.put("影响行数:",affectRows);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    @ApiOperation(value = "根据用户id，恢复逻辑删除的用户")
    @PutMapping("/recoverDeletedUserById/{userId}")
    public ResultObject<?> recoverDeletedUserById(
            @PathVariable("userId") Long userId){
        Long rows = iUserService.recoverDeletedUserById(userId);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数:",rows);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    @ApiOperation(value = "根据用户id，批量恢复逻辑删除的用户")
    @PutMapping("/recoverDeletedUserByIds")
    public ResultObject<?> recoverDeletedUserByIds(
            @RequestBody(required = false) Long[] userIds){

        Long rows = iUserService.recoverDeletedUserByIds(userIds);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数:",rows);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    @ApiOperation(value = "根据用户id，更新用户基本信息")
    @PutMapping("/update")
    public ResultObject<?> updateUser(
            @RequestBody(required = false) UserUpdateParam userUpdateParam){

        Long affectRows = iUserService.updateUser(userUpdateParam);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS, data);
    }

    @ApiOperation(value = "根据用户id，批量逻辑删除指定用户")
    @DeleteMapping("/logicallyDeleteUserByIds")
    public ResultObject<?> logicallyDeleteUserByIds(
            @RequestParam(required = false) Long[] userIds){

        if(userIds == null || userIds.length == 0){
            return ResultObject.failed(ResultCode.COLLECTION_NULL);
        }

        Long affectRows = iUserService.logicallyDeleteUserByIds(userIds);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS, data);
    }

    @ApiOperation(value = "根据用户id，逻辑删除指定用户")
    @DeleteMapping("/logicallyDeleteUserById")
    public ResultObject<?> logicallyDeleteUserById(
            @RequestParam(required = false) Long userId){

        Long affectRows = iUserService.logicallyDeleteUserById(userId);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS, data);
    }

    @ApiOperation(value = "根据用户id，物理删除指定用户", hidden = true)
    @DeleteMapping("/physicallyDeleteUserById")
    public ResultObject<?> physicallyDeleteUserById(
            @RequestBody(required = false) Long userId){

        Long affectRows = iUserService.physicallyDeleteUserById(userId);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS, data);
    }
}
