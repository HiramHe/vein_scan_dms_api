package hiram.image;

import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.service.InfraredService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 17:12
 * @Description: ""
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class InfraredServiceTest {

    @Autowired
    InfraredService infraredService;

    @Test
    public void insertOneTest(){
        InfraredDTO infraredDTO = new InfraredDTO();
        infraredDTO.setPerspective("正面");
        infraredDTO.setFilename("infraredTest.png");
        infraredDTO.setPath(null);
        infraredDTO.setPatientId(1l);
        infraredDTO.setUserId(1l);

        Infrared infrared = null;
        try {
            infrared = infraredService.insertOne(infraredDTO);
            System.out.println(infrared.toString());
        } catch (Exception e) {
            System.out.println("插入失败");
            System.out.println(e.getMessage());
        }
    }
}
