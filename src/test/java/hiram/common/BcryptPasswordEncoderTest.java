package hiram.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: HiramHe
 * @Date: 2020/4/29 13:06
 * @Description: ""
 */


public class BcryptPasswordEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345"));

        System.out.println(" ".trim().length());
    }
}
