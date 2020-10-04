package hiram.module.system.controller;

import hiram.common.enums.ResultCode;
import hiram.common.utils.MyStringUtils;
import hiram.common.utils.ObjectUtils;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.common.controller.BaseController;
import hiram.component.common.pojo.vo.TableData;
import hiram.module.system.pojo.dto.UserQueryRtDTO;
import hiram.module.system.pojo.dto.UserUpdateArgsDTO;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.po.UserRole;
import hiram.module.system.pojo.vo.UserInsertArgsVO;
import hiram.module.system.pojo.vo.UserQueryArgsVO;
import hiram.module.system.pojo.vo.UserUpdateArgsVO;
import hiram.module.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    /*
    查询
    */

    /*
    todo 返回vo给前端
    todo 参数校验
    todo 异常捕获
     */
    @ApiOperation(value = "查询用户列表")
    @PostMapping("/list")
    public ResultObject<?> list(
            @RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize,
            @RequestBody(required = false) UserQueryArgsVO userQueryArgsVO){

        if(logger.isDebugEnabled()){
            if(userQueryArgsVO !=null && userQueryArgsVO.getBeginTime() != null){
                System.out.println("beginTime:"+ userQueryArgsVO.getBeginTime());
                System.out.println("Type:"+ userQueryArgsVO.getBeginTime().getClass());
            }
        }

        this.startPage();
        List<UserQueryRtDTO> userQueryRtDTOs = iUserService.selectUserList(userQueryArgsVO);

        TableData tableData = this.getTableData(userQueryRtDTOs);

        return ResultObject.success(ResultCode.SUCCESS,tableData);
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
    @PutMapping("/add")
    public ResultObject<?> add(@RequestBody UserInsertArgsVO userInsertArgsVO){

        ////检查用户名和密码是否为空
        if(MyStringUtils.isEmpty(userInsertArgsVO.getUsername())  || MyStringUtils.isEmpty(userInsertArgsVO.getPassword())){
            return ResultObject.failed(ResultCode.USERNAME_PASSWORD_NULL);
        }

        //检查用户名唯一性
        boolean isUserNameUnique = iUserService.checkUserNameUnique(null,userInsertArgsVO.getUsername());
        if(isUserNameUnique){
            return ResultObject.failed(ResultCode.USER_EXIST);
        }

        //检查手机号是否已存在
        if(!MyStringUtils.isEmpty(userInsertArgsVO.getPhoneNumber()) && !iUserService.checkPhoneUnique(null,userInsertArgsVO.getPhoneNumber())){
            return ResultObject.failed(ResultCode.PHONENUMBER_NOT_UNIQUE);
        }

        //检查邮箱账号是否已存在
        if(!MyStringUtils.isEmpty(userInsertArgsVO.getEmail()) && !iUserService.checkEmailUnique(null,userInsertArgsVO.getEmail())){
            return ResultObject.failed(ResultCode.EMAIL_NOT_UNIQUE);
        }

        SysUser sysUser = iUserService.insertUser(userInsertArgsVO);

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
            @RequestBody(required = false) Long[] userIds){

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
    @PostMapping("/update")
    public ResultObject<?> updateUser(
            @Valid @RequestBody UserUpdateArgsVO userUpdateArgsVO){

        Long userId = userUpdateArgsVO.getUserId();

        //校验userId
        if (userId == null || userId<=0){
            return ResultObject.failed(ResultCode.WRONG_USERID);
        }

        //校验所有参数是否都为null
        if (
                MyStringUtils.isEmpty(userUpdateArgsVO.getNickname())
                && MyStringUtils.isEmpty(userUpdateArgsVO.getRealName())
                && MyStringUtils.isEmpty(userUpdateArgsVO.getSex())
                && MyStringUtils.isEmpty(userUpdateArgsVO.getEmail())
                && ObjectUtils.isNull(userUpdateArgsVO.getBirthday())
                && MyStringUtils.isEmpty(userUpdateArgsVO.getPhoneNumber())
                && MyStringUtils.isEmpty(userUpdateArgsVO.getAvatar())
                && MyStringUtils.isEmpty(userUpdateArgsVO.getRemark())
                && ObjectUtils.isNull(userUpdateArgsVO.getEnabled())
        ){
            return ResultObject.success(ResultCode.SUCCESS_NOACTION);
        }

        //校验用户名唯一性
        String username = userUpdateArgsVO.getUsername();
        if (username!=null){
            boolean isUserNameUnique = iUserService.checkUserNameUnique(userId,username);

            if (!isUserNameUnique){
                return ResultObject.failed(ResultCode.USER_EXIST);
            }
        }

        //校验email唯一性
        String email = userUpdateArgsVO.getEmail();
        if (email!=null){
            boolean isEmailUnique = iUserService.checkEmailUnique(userId,email);

            if (!isEmailUnique){
                return ResultObject.failed(ResultCode.EMAIL_NOT_UNIQUE);
            }
        }

        //校验手机号唯一性
        String phoneNumber = userUpdateArgsVO.getPhoneNumber();
        if (phoneNumber!=null){
            boolean isPhoneUnique = iUserService.checkPhoneUnique(userId,phoneNumber);

            if (!isPhoneUnique){
                return ResultObject.failed(ResultCode.PHONENUMBER_NOT_UNIQUE);
            }
        }

        //处理roleIds
        Long[] roleIds = userUpdateArgsVO.getRoleIds();
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
        UserUpdateArgsDTO userUpdateArgsDTO = new UserUpdateArgsDTO();
        BeanUtils.copyProperties(userUpdateArgsVO,userUpdateArgsDTO);
        if (userRoleList!=null && userRoleList.size()>0){
            userUpdateArgsDTO.setUserRoleList(userRoleList);
        }

        Long affectRows;
        try {
            affectRows = iUserService.updateUser(userUpdateArgsDTO);
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
            @RequestBody(required = false) Long userId){

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
    @PostMapping(value = "/resetUserPwd")
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
