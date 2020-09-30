package hiram.image;

import hiram.module.image.pojo.dto.BUltrasoundDTO;
import hiram.module.image.pojo.dto.InfraredDTO;
import hiram.module.image.pojo.entity.BUltrasound;
import hiram.module.image.pojo.entity.Infrared;
import hiram.module.image.service.BUltrasoundService;
import hiram.module.image.service.InfraredService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Author: HiramHe
 * @Date: 2020/9/28 17:12
 * @Description: ""
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BUltrasoundServiceTest {

    @Autowired
    BUltrasoundService bUltrasoundService;

    @Test
    public void insertOneTest(){
        BUltrasoundDTO bUltrasoundDTO = new BUltrasoundDTO();
        bUltrasoundDTO.setFilename("bultrasoundTest.png");
        bUltrasoundDTO.setPath(null);

        BUltrasound bUltrasound = null;
        try {
            bUltrasound = bUltrasoundService.insertOne(bUltrasoundDTO);
            System.out.println(bUltrasound.toString());
        } catch (Exception e) {
            System.out.println("插入失败");
            System.out.println(e.getMessage());
        }
    }
}
