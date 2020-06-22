//package hiram.module.system.filter;
//
//import hiram.module.system.domain.entity.SysUser;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collection;
//
///**
// * @Author: HiramHe
// * @Date: 2020/4/29 17:17
// * @Description: "身份校验"
// */
//
///**
// * token校验过滤器
// * BasicAuthenticationFilter 继承 OncePerRequestFilter
// */
//public class JwtVerifyFilter extends BasicAuthenticationFilter {
//
//    public JwtVerifyFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader("Authorization");
//
//        if(header != null && header.startsWith("Bearer ")){
//
//            /**
//             * todo 没有携带token，或者携带错误格式的token，则提示用户要登录
//             */
//
//
//        }else{
//            /**
//             * 携带了正确的token，则验证
//             */
//
//            String token = header.replace("Bearer ","");
//
//            /**
//             * todo 验证token
//             */
//
//            // 从token中拿到用户对象
//            SysUser sysUser = null;
//
//            //认证用户对象
//            if( sysUser !=null ){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(
//                                sysUser.getUsername(),
//                                null,
//                                (Collection<? extends GrantedAuthority>) sysUser.getSysRoles());
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//
//        chain.doFilter(request, response);
//    }
//}
