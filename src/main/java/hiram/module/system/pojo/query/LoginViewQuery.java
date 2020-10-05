package hiram.module.system.pojo.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: HiramHe
 * @Date: 2020/6/26 14:18
 * @Description: ""
 */

@Data
public class LoginViewQuery {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

}
