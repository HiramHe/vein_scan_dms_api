package hiram.module.system.controller;

import hiram.common.enums.ResultCode;
import hiram.common.utils.MyStringUtils;
import hiram.common.utils.ObjectUtils;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.common.controller.BaseController;
import hiram.component.common.pojo.vo.TableData;
import hiram.module.system.pojo.dto.UserSelectDTO;
import hiram.module.system.pojo.query.*;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.po.UserRole;
import hiram.module.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /*
    查询
    */

    /*
    todo 返回vo给前端
    todo 参数校验
    todo 异常捕获
     */
    @ApiOperation(value = "查询用户列表")
    @GetMapping("/list")
    public ResultObject<?> list(
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize,
            UserListViewQuery userListViewQuery){

        if(logger.isDebugEnabled()){
            if(userListViewQuery !=null && userListViewQuery.getBeginTime() != null){
                logger.debug("beginTime:"+ userListViewQuery.getBeginTime());
                logger.debug("Type:"+ userListViewQuery.getBeginTime().getClass());
            }
        }

        //将vo转化为dto
        UserListServiceQuery userListServiceQuery = new UserListServiceQuery();
        if (userListViewQuery != null){
            BeanUtils.copyProperties(userListViewQuery, userListServiceQuery);
        }

        this.startPage();
        List<UserSelectDTO> userSelectDTOs = iUserService.selectUserList(userListServiceQuery);

        TableData tableData = this.getTableData(userSelectDTOs);

        return ResultObject.success(ResultCode.SUCCESS,tableData);
    }

    @ApiOperation(value = "根据用户id获取用户信息")
    @GetMapping("/query/{userId}")
    public ResultObject<?> query(@PathVariable(name = "userId",required = true) Long userId){

        UserSelectDTO userSelectDTO = iUserService.selectUserByUserId(userId);

        if (userSelectDTO == null){
            return ResultObject.failed(ResultCode.RECORD_NOT_EXIST);
        }

        Map<String,UserSelectDTO> data = new HashMap<>();
        data.put("user",userSelectDTO);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    /*
    插入
    */

    /*
    done 校验参数
    done 插入用户
    todo 设置用户默认角色
    todo 捕获异常
    todo 返回vo给前端
     */
    @ApiOperation(value = "添加系统用户")
    @PostMapping("/add")
    public ResultObject<?> add(@Valid UserInsertViewQuery userInsertViewQuery){

        ////检查用户名和密码是否为空
        if(MyStringUtils.isEmpty(userInsertViewQuery.getUsername())  || MyStringUtils.isEmpty(userInsertViewQuery.getPassword())){
            return ResultObject.failed(ResultCode.USERNAME_PASSWORD_NULL);
        }

        //检查用户名唯一性
        boolean isUserNameUnique = iUserService.checkUserNameUnique(null, userInsertViewQuery.getUsername());
        if(!isUserNameUnique){
            return ResultObject.failed(ResultCode.USER_EXIST);
        }

        //检查手机号是否已存在
        if(!MyStringUtils.isEmpty(userInsertViewQuery.getPhoneNumber()) && !iUserService.checkPhoneUnique(null, userInsertViewQuery.getPhoneNumber())){
            return ResultObject.failed(ResultCode.PHONENUMBER_NOT_UNIQUE);
        }

        //检查邮箱账号是否已存在
        if(!MyStringUtils.isEmpty(userInsertViewQuery.getEmail()) && !iUserService.checkEmailUnique(null, userInsertViewQuery.getEmail())){
            return ResultObject.failed(ResultCode.EMAIL_NOT_UNIQUE);
        }

        //封装vo到dto中，不修改vo中的值
        UserInsertServiceQuery userInsertServiceQuery = new UserInsertServiceQuery();
        BeanUtils.copyProperties(userInsertViewQuery, userInsertServiceQuery);
        userInsertServiceQuery.setPassword(passwordEncoder.encode(userInsertViewQuery.getPassword()));

        SysUser sysUser = iUserService.insertUser(userInsertServiceQuery);

        Map<String,SysUser> data = new HashMap<>();
        data.put("user:",sysUser);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    /*
    恢复
    */

    /*
    todo 完善
     */
    @ApiOperation(value = "根据用户id，恢复逻辑删除的用户")
    @PutMapping("/recoverDeletedUserById/{userId}")
    public ResultObject<?> recoverDeletedUserById(
            @PathVariable("userId") Long userId){

        Long rows = iUserService.recoverDeletedUserById(userId);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数:",rows);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    /*
    todo 完善
     */
    @ApiOperation(value = "根据用户id，批量恢复逻辑删除的用户")
    @PutMapping("/recoverDeletedUserByIds")
    public ResultObject<?> recoverDeletedUserByIds(
            @RequestParam(required = false) Long[] userIds){

        Long rows = iUserService.recoverDeletedUserByIds(userIds);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数:",rows);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }

    /*
    更新
    */

    /*
    done 校验参数
    done 更新用户角色
    done 捕获异常
     */
    @ApiOperation(value = "根据用户id，更新用户基本信息")
    @PutMapping("/update")
    public ResultObject<?> updateUser(@Valid UserUpdateViewQuery userUpdateViewQuery){

        Long userId = userUpdateViewQuery.getUserId();

        //校验userId
        if (userId == null || userId<=0){
            return ResultObject.failed(ResultCode.WRONG_USERID);
        }

        //校验所有参数是否都为null
        if (
                MyStringUtils.isEmpty(userUpdateViewQuery.getNickname())
                && MyStringUtils.isEmpty(userUpdateViewQuery.getRealName())
                && MyStringUtils.isEmpty(userUpdateViewQuery.getSex())
                && MyStringUtils.isEmpty(userUpdateViewQuery.getEmail())
                && ObjectUtils.isNull(userUpdateViewQuery.getBirthday())
                && MyStringUtils.isEmpty(userUpdateViewQuery.getPhoneNumber())
                && MyStringUtils.isEmpty(userUpdateViewQuery.getAvatar())
                && MyStringUtils.isEmpty(userUpdateViewQuery.getRemark())
                && ObjectUtils.isNull(userUpdateViewQuery.getEnabled())
        ){
            return ResultObject.success(ResultCode.SUCCESS_NOACTION);
        }

        //校验用户名唯一性
        String username = userUpdateViewQuery.getUsername();
        if (username!=null){
            boolean isUserNameUnique = iUserService.checkUserNameUnique(userId,username);

            if (!isUserNameUnique){
                return ResultObject.failed(ResultCode.USER_EXIST);
            }
        }

        //校验email唯一性
        String email = userUpdateViewQuery.getEmail();
        if (email!=null){
            boolean isEmailUnique = iUserService.checkEmailUnique(userId,email);

            if (!isEmailUnique){
                return ResultObject.failed(ResultCode.EMAIL_NOT_UNIQUE);
            }
        }

        //校验手机号唯一性
        String phoneNumber = userUpdateViewQuery.getPhoneNumber();
        if (phoneNumber!=null){
            boolean isPhoneUnique = iUserService.checkPhoneUnique(userId,phoneNumber);

            if (!isPhoneUnique){
                return ResultObject.failed(ResultCode.PHONENUMBER_NOT_UNIQUE);
            }
        }

        //处理roleIds
        Long[] roleIds = userUpdateViewQuery.getRoleIds();
        List<UserRole> userRoleList = null;
        if (roleIds != null && roleIds.length > 0){
            userRoleList = new ArrayList<>();
            for(Long roleId:roleIds){
                if (roleId<=0){
                    return ResultObject.failed(ResultCode.FAILED_NOACTION);
                }

                UserRole ur = new UserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                userRoleList.add(ur);
            }
        }

        //将vo转换为dto
        UserUpdateServiceQuery userUpdateServiceQuery = new UserUpdateServiceQuery();
        BeanUtils.copyProperties(userUpdateViewQuery, userUpdateServiceQuery);
        if (userRoleList!=null && userRoleList.size()>0){
            userUpdateServiceQuery.setUserRoleList(userRoleList);
        }

        Long affectRows;
        try {
            affectRows = iUserService.updateUser(userUpdateServiceQuery);
        } catch (Exception e) {
            return ResultObject.failed(ResultCode.FAILED);
        }

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS, data);
    }

    /*
    删除
    */

    /*
    todo 完善
     */
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

    /*
    todo 完善
     */
    @ApiOperation(value = "根据用户id，物理删除指定用户", hidden = true)
    @DeleteMapping("/physicallyDeleteUserById")
    public ResultObject<?> physicallyDeleteUserById(
            @RequestParam(required = false) Long userId){

        Long affectRows = iUserService.physicallyDeleteUserById(userId);

        Map<String,Long> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS, data);
    }

    /*
    done 功能实现
    done 错误提示
     */
    @ApiOperation(value = "重置用户密码")
    @PutMapping(value = "/resetUserPwd")
    public ResultObject<?> resetUserPwd(@RequestParam Long userId,
                                        @RequestParam String newPassword){

        int affectRows = iUserService.resetUserPwd(userId, newPassword);

        if (affectRows<=0){
            return ResultObject.failed(ResultCode.FAILED);
        }

        Map<String,Integer> data = new HashMap<>();
        data.put("影响行数",affectRows);

        return ResultObject.success(ResultCode.SUCCESS,data);
    }
}
