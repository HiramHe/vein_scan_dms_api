package hiram.component.security.filter;

import hiram.component.common.service.ITokenService;
import hiram.component.common.pojo.vo.LoginUser;
import hiram.component.security.exception.CustomAuthenticationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @Author: HiramHe
 * @Date: 2020/4/29 17:17
 * @Description: "身份校验"
 */

/*
 * 非登录业务
 * token校验过滤器
 * BasicAuthenticationFilter 继承 OncePerRequestFilter
 */

/*
 * 不妨参照BasicAuthenticationFilter的doFilterInternal方法，
 * 看看它是怎么捕获并处理异常的。
 */

/*
只要请求中携带了token，且token不为空，本过滤器就会去验证token；
否则，本过滤器对系统不产生任何影响，换言之，如果没带token，或者token为空，
则本过滤器直接掠过，继续走过滤器链。
 */

//@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtVerifyFilter extends OncePerRequestFilter {

    AuthenticationEntryPoint authenticationEntryPoint;
    UserDetailsService userDetailsService;
    ITokenService myTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
         携带了token，验证
        */

        /*
        方式一：done
        直接验证token本身的时效
        */

        /*
        方式二：done
        使用redis
        登录成功获得token后，将token存储到redis
        如果redis中的token版本过期，重新刷新获取新的token。
        注意：刷新获得新token是在token有效时间内进行。
        如果token本身已过期，强制登录，生成新token。
        */

        try{

            // 通过token从redis中拿到用户
            LoginUser loginUser = myTokenService.getLoginUser(request);

            //认证用户
            if( loginUser !=null ){
                /*
                通过token拿到用户信息得到UserDetails,需要查询数据库。
                也可以直接使用token中携带的用户信息，不需要查询数据库。
                 */
                String username = "";
                username = loginUser.getUser().getUsername();
                Collection<? extends GrantedAuthority> authorities = loginUser.getAuthorities();

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        } catch (ExpiredJwtException expiredJwtException){
            /*
            此处参考了BasicAuthenticationFilter对异常的处理
             */
            /*
             * 视作认证异常，交给authenticationEntryPoint去处理。
             */

            SecurityContextHolder.clearContext();

            this.authenticationEntryPoint.commence(
                    request,
                    response,
                    new CustomAuthenticationException("token已过期，请重新登录"));

            /*
             * 不继续走 过滤器链 了
             */
            return;

        } catch (JwtException otherJwtException){
            /*
            UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException
             */
            SecurityContextHolder.clearContext();

            this.authenticationEntryPoint.commence(
                    request,
                    response,
                    new CustomAuthenticationException("token错误，非本系统签发."));

            return;
        }catch (UsernameNotFoundException usernameNotFoundException){
            SecurityContextHolder.clearContext();

            this.authenticationEntryPoint.commence(
                    request,
                    response,
                    usernameNotFoundException);

            return;

        } catch (Exception e) {
            e.printStackTrace();

            return;

        }

        chain.doFilter(request, response);
    }

}
