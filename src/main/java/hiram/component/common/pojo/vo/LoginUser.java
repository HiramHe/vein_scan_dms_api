package hiram.component.common.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hiram.module.system.pojo.po.SysRole;
import hiram.module.system.pojo.po.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: HiramHe
 * @Date: 2020/5/15 12:43
 * @Description: ""
 */

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     * private Collection<? extends GrantedAuthority> authorities;
     */
    private Collection<SysRole> sysRoles;

    /**
     * 用户信息
     */
    private SysUser user;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return sysRoles;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
