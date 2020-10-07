package hiram.module.patient.controller;

import hiram.common.enums.ResultCodeEnum;
import hiram.common.utils.MyStringUtils;
import hiram.component.common.controller.BaseController;
import hiram.component.common.pojo.vo.ResultObject;
import hiram.module.patient.pojo.po.Patient;
import hiram.module.patient.pojo.query.PatientAddServiceQuery;
import hiram.module.patient.pojo.query.PatientAddViewQuery;
import hiram.module.patient.pojo.query.PatientSelectViewQuery;
import hiram.module.patient.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/10/5 14:20
 * @Description: ""
 */

/*
不管是什么请求，参数都可以是对象；如果是对象，不要使用@RequestParam注解。
如果使用了@RequestBody注解，那么接收的需要是一个json。
 */

@Api(tags = "患者管理接口")
@RestController
@RequestMapping("/patient")
public class PatientController extends BaseController {

    @Autowired
    PatientService patientService;

    @ApiOperation(value = "查询患者列表",hidden = true)
    @GetMapping
    public ResultObject<?> list(
            @RequestParam(required = false,defaultValue = "1") Long pageNum,
            @RequestParam(required = false,defaultValue = "5") Long pageSize,
            PatientSelectViewQuery patientSelectViewQuery
    ){
        startPage();
        return null;
    }

    @ApiOperation("根据患者id获取患者信息")
    @GetMapping("/{patientId}")
    public ResultObject<?> selectPatientByPatientId(@PathVariable(name = "patientId") Long patientId){

        Patient patient = patientService.selectPatientByPatientId(patientId);

        if (patient == null){
            return ResultObject.failed(ResultCodeEnum.RECORD_NOT_EXIST);
        }

        Map<String,Patient> data = new HashMap<>();
        data.put("patient",patient);

        return ResultObject.success(ResultCodeEnum.SUCCESS,data);
    }

    @ApiOperation("新增患者")
    @PostMapping
    public ResultObject<?> add(@Valid PatientAddViewQuery patientAddViewQuery){

        PatientAddServiceQuery patientAddServiceQuery;
        if (patientAddViewQuery != null){
            patientAddServiceQuery = new PatientAddServiceQuery();
            BeanUtils.copyProperties(patientAddViewQuery,patientAddServiceQuery);
        }else {
            return ResultObject.failed(ResultCodeEnum.FAILED);
        }

        //校验姓名唯一性
        if (!MyStringUtils.isEmpty(patientAddViewQuery.getPatientName())){

            Boolean isPatientNameUnique = patientService.checkPatientNameUnique(null, patientAddViewQuery.getPatientName());
            if (!isPatientNameUnique){
                return ResultObject.failed(ResultCodeEnum.NAME_EXIST);
            }
        }

        //校验电话号码唯一性
        if (!MyStringUtils.isEmpty(patientAddViewQuery.getPhoneNumber())){

            Boolean isPhoneNumberUnique = patientService.checkPhoneNumberUnique(null, patientAddViewQuery.getPhoneNumber());
            if (!isPhoneNumberUnique){
                return ResultObject.failed(ResultCodeEnum.PHONENUMBER_NOT_UNIQUE);
            }
        }

        //校验电子邮件唯一性
        if (!MyStringUtils.isEmpty(patientAddViewQuery.getEmail())){

            Boolean isEmailUnique = patientService.checkEmailUnique(null, patientAddViewQuery.getEmail());
            if (!isEmailUnique){
                return ResultObject.failed(ResultCodeEnum.EMAIL_NOT_UNIQUE);
            }
        }

        Patient patient = patientService.insertOne(patientAddServiceQuery);

        if (patient == null){
            return ResultObject.failed(ResultCodeEnum.FAILED);
        }

        Map<String,Patient> data = new HashMap<>();
        data.put("patient",patient);

        return ResultObject.success(ResultCodeEnum.SUCCESS,data);
    }
}
