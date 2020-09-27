package hiram.component.security.configuration;


import hiram.component.common.service.ITokenService;
import hiram.component.security.filter.CustomUsernamePasswordAuthenticationFilter;
import hiram.component.security.filter.JwtVerifyFilter;
import hiram.component.security.handler.CustomAccessDeniedHandler;
import hiram.component.security.handler.CustomAuthenticationFailureHandler;
import hiram.component.security.handler.CustomAuthenticationSuccessHandler;
import hiram.component.security.handler.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;


/**
 * @Author: HiramHe
 * @Date: 2020/4/28 10:49
 * @Description: "spring security授权配置主文件"
 */

/*
 @Configuration ：会将当前类放进ioc容器
*/

/*
自定义的filter，不要配置@Component来加入容器，@Bean的形式也不可以。
应该在需要的时候，把这个过滤器new出来。
因为加入到容器的话，Spring security的 webSecurity.ignoring()配置不会忽略自定义过滤器。
因此，AuthenticationEntryPoint也不能在自定义过滤器中自动注入，可以在new过滤器的时候，传进去。
 */

//@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ITokenService iTokenService;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * 认证
     * 配置认证用户的来源
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        /*
        梳理UsernamePasswordAuthenticationFilter的工作流程来分析认证过程，才方便理解下述设置。
        spring security本身提供了"/login"的控制器，不需要我们自己写。
        UsernamePasswordAuthenticationFilter中的UsernamePasswordAuthenticationToken一开
         始只保存了前端传过来的用户名、密码。
        设置认证数据的来源：来自数据库。（我们也可以设置来源为内存，在内存中保存几个用户的信息，一般测试用）
        设置认证管理器的UserDetailsService为我们自己实现的CustomUserDetailsServiceImpl，
        UserDetailsService拿到UserDetails(数据库中的)后，由AuthenticationManager接口的
         实现类负责跟前端传过来的用户名、密码进行比较，一致则认证成功，否则认证失败。
         */
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                //设置AuthenticationManager认证时使用的加密工具
                .passwordEncoder(passwordEncoder)
        ;
    }


    /**
     * 绕过spring security
     * 一般用来配置放行静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //放行swagger
                .ignoring().antMatchers("/webjars/**")
                .and()
                .ignoring().antMatchers("/swagger-ui.html")
                .and()
                .ignoring().antMatchers("/v2/api-docs")
                .and()
                .ignoring().antMatchers("/swagger-resources/**")
                .and()
                // 放行静态资源
                .ignoring().antMatchers("/static/**")
                .and()
//                .ignoring().antMatchers("/common/**")
        ;
    }


    /**
     * 资源授权。
     * 不会绕过spring security。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
         * csrf配置。
         * 释放静态资源。本项目为前后端分离，不存在静态资源放行，无需设置。
         * 指定资源拦截规则。
         * 指定自定义认证页面。前后端分离，无需设置。
         * 指定退出认证配置。
         * 如果要把各个配置的项写在一起，用 and() 连接。
        */

        /*
        1.解决跨域问题。cors预检请求放行，让spring security放行
        所有preflight request（cors预检请求）
        */
        http
                .authorizeRequests()
                .requestMatchers(CorsUtils::isCorsRequest).permitAll()
        ;

        /*
        2.让security永远不会创建HttpSession，它不会使用HttpSession来获取SecurityContext
         */
        http
                // CRSF禁用，因为不使用session
                // 去掉csrf拦截的过滤器
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        /*
        3.配置资源权限，给资源配置角色。
        配置到此处，说明用户已经认证了。其中，匿名用户有一个匿名的认证。
        放行登录API请求，其他任何请求都必须经过权限控制。
        也可以在controller那里配置资源权限。
        access(""):自定义授权表达式，参数写spring的EL表达式，作用与配置 hasRole("")相同。
        */
        /*
        静态授权
        */
        http
                //拿到ExpressionUrlAuthorizationConfigurer对象
                .authorizeRequests()

                //当前资源，配置为所有角色均可访问，用户需要认证。
                //访问时依然需要走过滤器链。
                //也就是说，如果请求时携带了token，token依然会被过滤器拦截并解析。
                // 对于登录login 允许匿名访问
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/system/user/profile").permitAll()
//                .antMatchers(HttpMethod.GET,"/test").permitAll()

                //所有资源，配置为用户都有的角色：USER 角色，用户需要认证。
//                .antMatchers("/**").hasAnyRole("USER")

                //当前资源，配置为匿名角色才能访问，用户需要认证。
                //但是已认证用户会无权访问该资源。
                //访问时依然需要走过滤器链。
//                .antMatchers("/test").anonymous()

                //当前资源，配置为指定角色才能访问，用户需要认证
//                .antMatchers("/test").hasAnyRole("ADMIN")
        ;

        /*
        动态授权
        */
        /*http.authorizeRequests()
                .anyRequest().access("")
        ;*/

        /*
        其他资源，没有配置角色，用户首先需要认证
        */
        http.authorizeRequests()
                .anyRequest().authenticated()
        ;

        /*添加过滤器*/
        /*4.拦截账号、密码。覆盖UsernamePasswordAuthenticationFilter过滤器*/
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter =
                new CustomUsernamePasswordAuthenticationFilter();
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        //配置要拦截的请求
        customUsernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login");
        http.addFilterAt(customUsernamePasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        /*5.拦截token，并检测。在UsernamePasswordAuthenticationFilter之前添加*/
        /*JwtVerifyFilter*/
        JwtVerifyFilter jwtVerifyFilter = new JwtVerifyFilter();
        jwtVerifyFilter.setAuthenticationEntryPoint(authenticationEntryPoint);
        jwtVerifyFilter.setUserDetailsService(userDetailsService);
        jwtVerifyFilter.setMyTokenService(iTokenService);
        http.addFilterBefore(jwtVerifyFilter,UsernamePasswordAuthenticationFilter.class);

        /*
         6.配置异常处理
         spring security主要有两大异常:
         认证异常:认证失败异常401,AuthenticationException，
         权限异常。
         看ExceptionTranslationFilter源代码可知，碰到异常时，
         该过滤器会先看是不是认证异常，如果不是，再看是不是权限异常。最后再去处理异常，
         也就是说，如果先碰到了认证异常，就不会去看权限异常了，直接去处理异常。
         处理认证异常时，该过滤器会调用这里配置的AuthenticationEntryPoint的commence方法。
         处理权限异常时，该过滤器会调用这里配置的AccessDeniedHandler的handle方法。
        */
        /*
        认证异常.
        屏蔽重定向的登录页面，并返回统一的json格式的返回体。
        未登录用户访问资源时，不会再跳到（默认spring security提供的）登录页了。
        */
        http
                //拿到ExceptionHandlingConfigurer
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);
        /*
        权限异常:已登录成功，没有权限异常403,AccessDeniedException
        */
        http
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);

        /*
        7.
        配置登录方式。各种登录方式均是 AbstractAuthenticationFilterConfigurer 实现的。
        开启openidLogin的登录方式，需要向过滤器链添加OpenIDAuthenticationFilter。
        开启formLogin的登录方式，普通表单登录，即用户名和密码的登录形式，
        会向过滤器链加入UsernamePasswordAuthenticationFilter；
        配置loginProcessingUrl，是为了修改loginProcessingUrl，告诉
        UsernamePasswordAuthenticationFilter哪个是处理登录的请求，从而让它去拦截并处理。
        但是UsernamePasswordAuthenticationFilter又无法读取json格式的数据，
        UsernamePasswordAuthenticationFilter我们是不使用的，因此登录方式就不用配置了。
        */
//        http
//                .formLogin()
                //指定登录页，默认提供的登录页为 /login，指定的处理器为 /login。
//                .loginPage("/login.jsp")
                //配置处理器地址，默认为 /login，处理器由spring security提供；
                // 修改后，提交的数据会到该url的处理器，
                //且中途会被UsernamePasswordAuthenticationFilter拦截。
                // 默认提供的登录页的按钮也会将数据提交到这个地址；
                //我们可以实现url的处理器，但是如果在过滤器中已经处理了登录数据，
                // 就没必要实现处理器了。
//                .loginProcessingUrl("/user/login")
//                .successForwardUrl("/index.jsp")
//                .failureForwardUrl("/failer.jsp")
//
                //释放这里配置的页面和Url。释放静态资源时只是释放了页面，
                //但是/login处理器还是被拦住了，所以需要释放。
//
//                .permitAll()
//        ;


        /*
        8.退出.
        */
        http
            .logout()
            //logout处理器地址，spring security已经提供了实现，不用我们自己写
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandler)
            //清空session
            .invalidateHttpSession(true)
             //释放这里配置的页面和Url。释放静态资源时只是释放了页面，
             //但是/logout处理器还是被拦住了，所以需要释放。
            .permitAll()
        ;

    }

}
