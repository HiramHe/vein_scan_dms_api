package hiram.image;

import hiram.module.image.pojo.query.InfraredServiceQuery;
import hiram.module.image.pojo.po.Infrared;
import hiram.module.image.service.InfraredService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        InfraredServiceQuery infraredServiceQuery = new InfraredServiceQuery();
        infraredServiceQuery.setPerspective("正面");
        infraredServiceQuery.setFilename("infraredTest.png");
        infraredServiceQuery.setPath(null);
        infraredServiceQuery.setPatientId(1l);
        infraredServiceQuery.setUserId(1l);

        Infrared infrared = null;
        try {
            infrared = infraredService.insertOne(infraredServiceQuery);
            System.out.println(infrared.toString());
        } catch (Exception e) {
            System.out.println("插入失败");
            System.out.println(e.getMessage());
        }
    }
}
