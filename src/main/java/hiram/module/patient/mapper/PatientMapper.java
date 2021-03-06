package hiram.module.patient.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import hiram.module.patient.pojo.po.Patient;
import hiram.module.patient.pojo.query.PatientAddServiceQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:53
 * @Description: ""
 */

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {

    Integer insertOne(Patient patient);

    Patient checkPatientNameUnique(String patientName);

    Patient checkPhoneNumberUnique(String phoneNumber);

    Patient checkEmailUnique(String email);

    Patient selectPatientByPatientId(Long patientId);

}
