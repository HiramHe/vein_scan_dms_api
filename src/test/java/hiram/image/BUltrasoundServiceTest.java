package hiram.image;

import hiram.module.image.pojo.query.BUltrasoundServiceQuery;
import hiram.module.image.pojo.po.BUltrasound;
import hiram.module.image.service.BUltrasoundService;
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
public class BUltrasoundServiceTest {

    @Autowired
    BUltrasoundService bUltrasoundService;

    @Test
    public void insertOneTest(){
        BUltrasoundServiceQuery bUltrasoundServiceQuery = new BUltrasoundServiceQuery();
        bUltrasoundServiceQuery.setFilename("bultrasoundTest.png");
        bUltrasoundServiceQuery.setPath(null);

        BUltrasound bUltrasound = null;
        try {
            bUltrasound = bUltrasoundService.insertOne(bUltrasoundServiceQuery);
            System.out.println(bUltrasound.toString());
        } catch (Exception e) {
            System.out.println("插入失败");
            System.out.println(e.getMessage());
        }
    }
}
