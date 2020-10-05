package hiram.module.patient.service.impl;

import hiram.common.constant.Constants;
import hiram.module.patient.mapper.PatientMapper;
import hiram.module.patient.pojo.po.Patient;
import hiram.module.patient.pojo.query.PatientAddServiceQuery;
import hiram.module.patient.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 11:19
 * @Description: ""
 */

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;

    /**
     * 新增患者
     *
     * @param patientAddServiceQuery
     * @return
     */
    @Override
    public Patient insertOne(PatientAddServiceQuery patientAddServiceQuery) {

        Patient patient;
        if (patientAddServiceQuery!=null){
            patient = new Patient();
            BeanUtils.copyProperties(patientAddServiceQuery,patient);
        }else {
            return null;
        }

        Integer rt = patientMapper.insertOne(patient);

        if (rt>0){
            return patient;
        }else {
            return null;
        }
    }

    /**
     *
     * 插入和更新都需要用到
     *
     * 校验患者姓名唯一性
     *
     * @param patientId
     * @param patientName
     * @return
     */
    @Override
    public Boolean checkPatientNameUnique(Long patientId, String patientName) {

        patientId = patientId == null ? -1 : patientId;

        Patient patient = patientMapper.checkPatientNameUnique(patientName);

        if (patient!=null && !patient.getPatientId().equals(patientId)){
            return Constants.NOT_UNIQUE;
        }

        return Constants.UNIQUE;
    }

    /**
     * 校验电话号码唯一性
     *
     * @param patientId
     * @param phoneNumber
     * @return
     */
    @Override
    public Boolean checkPhoneNumberUnique(Long patientId, String phoneNumber) {

        patientId = patientId == null ? -1 : patientId;

        Patient patient = patientMapper.checkPhoneNumberUnique(phoneNumber);

        if (patient!=null && !patient.getPatientId().equals(patientId)){
            return Constants.NOT_UNIQUE;
        }

        return Constants.UNIQUE;
    }

    /**
     * 校验电子邮件唯一性
     *
     * @param patientId
     * @param email
     * @return
     */
    @Override
    public Boolean checkEmailUnique(Long patientId, String email) {

        patientId = patientId == null ? -1 : patientId;

        Patient patient = patientMapper.checkEmailUnique(email);

        if (patient!=null && !patient.getPatientId().equals(patientId)){
            return Constants.NOT_UNIQUE;
        }

        return Constants.UNIQUE;
    }

    /**
     * 根据患者id获取患者信息
     *
     * @param patientId
     * @return
     */
    @Override
    public Patient selectPatientByPatientId(Long patientId) {

        Patient patient = patientMapper.selectPatientByPatientId(patientId);

        return patient;
    }
}
