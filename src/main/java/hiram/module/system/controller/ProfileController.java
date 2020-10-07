package hiram.module.system.controller;

import hiram.common.utils.MyStringUtils;
import hiram.common.utils.ObjectUtils;
import hiram.component.common.service.ITokenService;
import hiram.common.utils.ServletUtils;
import hiram.common.enums.ResultCodeEnum;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.component.common.pojo.vo.LoginUser;
import hiram.module.system.pojo.query.ProfileUpdateViewQuery;
import hiram.module.system.pojo.query.UserUpdateServiceQuery;
import hiram.module.system.pojo.po.SysRole;
import hiram.module.system.pojo.po.SysUser;
import hiram.module.system.pojo.vo.*;
import hiram.module.system.service.IRoleService;
import hiram.module.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/5/14 21:32
 * @Description: ""
 */

@RestController
@RequestMapping("/profile")
@Api(tags = "个人中心接口")
public class ProfileController {

    @Autowired
    ITokenService iTokenService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService iUserService;

    @Autowired
    BCryptPasswordEncoder encoder;

    /*
    done 功能实现
    done 错误提示
     */
    @ApiOperation(value = "获取个人信息")
    @GetMapping("/getInfo")
    public ResultObject<?> profile() throws Exception {

        /*
        如果有token，则已经在request中携带了。
        抛出的异常会被全局异常处理器捕获并处理。
         */
        LoginUser loginUser = iTokenService.getLoginUser(ServletUtils.getRequest());

        ResultObject<Map<String,Object>> resultObject;

        if(loginUser != null){
            SysUser sysUser = loginUser.getUser();

            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(sysUser,userVO);

            List<SysRole> roles = roleService.selectRolesByUsername(sysUser.getUsername());
            List<RoleVO> roleVOs = new ArrayList<>();
            for (SysRole sysRole:roles){
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(sysRole,roleVO);
                roleVOs.add(roleVO);
            }

            Map<String,Object> data = new HashMap<>();
            data.put("user",userVO);
            data.put("roles",roleVOs);

            resultObject = ResultObject.success(ResultCodeEnum.SUCCESS,data);
        } else {
            resultObject = ResultObject.failed(ResultCodeEnum.LOGIN_EXPIRED);
        }

        return resultObject;
    }

    @ApiOperation(value = "修改密码")
    @PutMapping(value = "/updatePassword")
    public ResultObject<?> updatePassword(String oldPassword, String newPassword) throws Exception {
        LoginUser loginUser = iTokenService.getLoginUser(ServletUtils.getRequest());

        Long userId = loginUser.getUser().getUserId();
        String password = loginUser.getPassword();

        if (!encoder.matches(oldPassword,password)) {
            return ResultObject.failed(ResultCodeEnum.OLDPASSWORD_ERROR);
        }
        if( encoder.matches(newPassword,password)){
            return ResultObject.failed(ResultCodeEnum.NEWPASSWORD_SAME_ERROR);
        }

        if( iUserService.resetUserPwd(userId,newPassword) > 0){
            //更新缓存用户密码
            loginUser.getUser().setPassword(encoder.encode(newPassword));
            iTokenService.setLoginUser(loginUser);

            return ResultObject.success(ResultCodeEnum.SUCCESS);
        }

        return ResultObject.failed(ResultCodeEnum.RESETPASSWORD_ERROR);
    }

    /*
    done 修改数据库
    done 修改缓存
    done 错误处理
     */
    @ApiOperation(value = "修改个人信息")
    @PutMapping(value = "/updateProfile")
    public ResultObject<?> updateProfile(@Valid ProfileUpdateViewQuery profileUpdateViewQuery) throws Exception {

        //校验所有参数是否都为null
        if (
                MyStringUtils.isEmpty(profileUpdateViewQuery.getNickname())
                        && MyStringUtils.isEmpty(profileUpdateViewQuery.getRealName())
                        && MyStringUtils.isEmpty(profileUpdateViewQuery.getSex())
                        && MyStringUtils.isEmpty(profileUpdateViewQuery.getEmail())
                        && ObjectUtils.isNull(profileUpdateViewQuery.getBirthday())
                        && MyStringUtils.isEmpty(profileUpdateViewQuery.getPhoneNumber())
                        && MyStringUtils.isEmpty(profileUpdateViewQuery.getRemark())
        ){
            return ResultObject.success(ResultCodeEnum.SUCCESS_NOACTION);
        }

        LoginUser loginUser = iTokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();

        //校验用户名唯一性
        String username = profileUpdateViewQuery.getUsername();
        if (username!=null){
            boolean isUserNameUnique = iUserService.checkUserNameUnique(userId,username);

            if (!isUserNameUnique){
                return ResultObject.failed(ResultCodeEnum.USER_EXIST);
            }
        }

        //校验email唯一性
        String email = profileUpdateViewQuery.getEmail();
        if (email!=null){
            boolean isEmailUnique = iUserService.checkEmailUnique(userId,email);

            if (!isEmailUnique){
                return ResultObject.failed(ResultCodeEnum.EMAIL_NOT_UNIQUE);
            }
        }

        //校验手机号唯一性
        String phoneNumber = profileUpdateViewQuery.getPhoneNumber();
        if (phoneNumber!=null){
            boolean isPhoneUnique = iUserService.checkPhoneUnique(userId,phoneNumber);

            if (!isPhoneUnique){
                return ResultObject.failed(ResultCodeEnum.PHONENUMBER_NOT_UNIQUE);
            }
        }

        //将vo转换为dto
        UserUpdateServiceQuery userUpdateServiceQuery = new UserUpdateServiceQuery();
        BeanUtils.copyProperties(profileUpdateViewQuery, userUpdateServiceQuery);
        userUpdateServiceQuery.setUserId(userId);

        Long rt;
        try {
            rt = iUserService.updateUser(userUpdateServiceQuery);
        } catch (Exception e) {
            return ResultObject.failed(ResultCodeEnum.FAILED);
        }

        //更新缓存
        if (rt > 0){
            String nickname = profileUpdateViewQuery.getNickname();
            String realName = profileUpdateViewQuery.getRealName();
            String sex = profileUpdateViewQuery.getSex();
            LocalDate birthday = profileUpdateViewQuery.getBirthday();
            String remark = profileUpdateViewQuery.getRemark();

            if (!MyStringUtils.isEmpty(username)){
                loginUser.getUser().setUsername(username);
            }
            if (!MyStringUtils.isEmpty(nickname)){
                loginUser.getUser().setNickname(nickname);
            }
            if (!MyStringUtils.isEmpty(realName)){
                loginUser.getUser().setRealName(realName);
            }
            if (!MyStringUtils.isEmpty(sex)){
                loginUser.getUser().setSex(sex);
            }
            if (!ObjectUtils.isNull(birthday)){
                loginUser.getUser().setBirthday(birthday);
            }
            if (!MyStringUtils.isEmpty(email)){
                loginUser.getUser().setEmail(email);
            }
            if (!MyStringUtils.isEmpty(phoneNumber)){
                loginUser.getUser().setPhoneNumber(phoneNumber);
            }
            if (!MyStringUtils.isEmpty(remark)){
                loginUser.getUser().setRemark(remark);
            }

            iTokenService.setLoginUser(loginUser);

            return ResultObject.success(ResultCodeEnum.SUCCESS);
        }

        return ResultObject.failed(ResultCodeEnum.FAILED);
    }

    /*
    todo 功能实现
     */
    @ApiOperation(value = "用户头像",hidden = true)
    @PutMapping("/avatar")
    public ResultObject<?> avatar(@RequestParam MultipartFile file){


        return ResultObject.failed(ResultCodeEnum.FUNCTION_TODO);
    }
}
