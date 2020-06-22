//package hiram.module.system.configuration;
//
//
//import hiram.module.auth.filter.CustomUsernamePasswordAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.cors.CorsUtils;
//
//
///**
// * @Author: HiramHe
// * @Date: 2020/4/28 10:49
// * @Description: "spring security授权配置主文件"
// */
//
///**
// * 如果做成分布式系统，每个模块需要单独配置spring security
// * 在资源模块中，不用再做认证管理了，只需要做身份校验。
// */
//@EnableWebSecurity
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 配置spring security相关信息
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        /**
//         * csrf配置。
//         * 指定资源拦截规则。
//         * 如果要把各个配置的项写在一起，用 and() 连接。
//         */
//
//        //1.解决跨域问题。cors预检请求放行，让spring security放行所有preflight request（cors预检请求）
//        http.authorizeRequests().requestMatchers(CorsUtils::isCorsRequest).permitAll();
//
//        //2.让security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
//        http
//                // CRSF禁用，因为不使用session
//                // 去掉csrf拦截的过滤器
//                .csrf().disable()
//                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        //3.指定资源拦截规则
//        /**
//         * 放行登录API请求，其他任何请求都必须经过权限控制。
//         * access(""):参数写spring的EL表达式，等价于配置 hasRole("")。
//         * 配置api的权限，两种方法：
//         * 1.在此处动态配置每一个api需要的角色权限；
//         * 2.不在此处配置，而是在controller的方法上指定该api需要的角色权限。
//         */
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
//                //所有用户都有的最基本的角色：USER 角色。
//                .antMatchers("/**").hasAnyRole("USER")
//                //动态加载资源
////                .anyRequest().access("")
//                //其他资源，必须认证之后才能访问
//                .anyRequest().authenticated();
//
//        /**
//         * 添加过滤器
//         */
//
//        /**
//         * 4.添加认证过滤器，拦截账号、密码。覆盖UsernamePasswordAuthenticationFilter过滤器。
//         * 不需要了
//         */
////        http.addFilterAt()
//
//        /**
//         * 5.添加校验过滤器，拦截token，并检测。
//         * 保留
//         */
//
//
//        //6.处理异常情况
//
//
//    }
//
//}
