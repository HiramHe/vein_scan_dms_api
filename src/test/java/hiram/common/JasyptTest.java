package hiram.common;

import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleAsymmetricStringEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: HiramHe
 * @Date: 2020/6/24 14:00
 * @Description: ""
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class JasyptTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Autowired
    private ApplicationContext applicationContext;

    private String mysqlPassword = "0000";


    @Test
    public void testEncrypt(){
        String encryptedPassword = encrypt(mysqlPassword);
        System.out.println("encryptedPassword:"+encryptedPassword);
    }

    @Test
    public void testDecrypt(){
        Environment environment = applicationContext.getBean(Environment.class);
        String password = environment.getProperty("spring.datasource.password");
        Assert.assertEquals(mysqlPassword,password);
    }

    private String encrypt(String originStr){
        return stringEncryptor.encrypt(originStr);
    }

    private String decrypt(String encryptedStr){
        return stringEncryptor.decrypt(encryptedStr);
    }

}
