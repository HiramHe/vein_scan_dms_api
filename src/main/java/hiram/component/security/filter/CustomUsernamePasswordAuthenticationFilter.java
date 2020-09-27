package hiram.component.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hiram.component.security.exception.CustomAuthenticationException;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @Author: HiramHe
 * @Date: 2020/4/28 21:28
 * @Description: "用户认证"
 */

/**
 * 登录业务使用。
 * 认证过滤器：重写UsernamePasswordAuthenticationFilter过滤器
 * 参照UsernamePasswordAuthenticationFilter里面的实现，修改部分代码
 * UsernamePasswordAuthenticationFilter继承AbstractAuthenticationProcessingFilter.
 *
 * 1.UsernamePasswordAuthenticationFilter只支持post请求，不能处理json
 */


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /*
    logger extends from GenericFilterBean
     */

    /**
     * 本过滤器需要用到AuthenticationManager, WebSecurityConfigurerAdapter中创建
     * 了AuthenticationManager，因此在我们自己的spring security配置类中，
     * 在向过滤器链添加本过滤器时，把WebSecurityConfigurerAdapter中创建的
     * AuthenticationManager对象拿过来，再传到这里。
     * 由于spring security的AuthenticationManager不在容器中，故不能使用自动注入。
     */
    AuthenticationManager authenticationManager;

    public CustomUsernamePasswordAuthenticationFilter() {

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if(logger.isDebugEnabled()){
            logger.debug("自定义用户名密码过滤器尝试认证.");
            logger.debug("request的ContentType:"+request.getContentType());
        }

        //UsernamePasswordAuthenticationFilter也能拦截处理登录的请求，
        //但是只能获取以表单形式提交的数据，无法获取json格式的数据。
        //修改为支持从json中解析username和password，而不仅仅限于form表单。
        if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)){

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String,String> requestMap = null;

            //使用try with resource，自动释放资源
            try(InputStream inputStream = request.getInputStream()){
                requestMap = objectMapper.readValue(inputStream,Map.class);
            } catch (IOException e) {
                //抛出异常
                throw new CustomAuthenticationException(e.getMessage());
            }

            /**
             * UsernamePasswordAuthenticationToken继承了AbstractAuthenticationToken
             * AbstractAuthenticationToken实现了AuthenticationToken
             */
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
            if( !requestMap.isEmpty() ){
                //拿到请求中的账号、密码
                String username = requestMap.get(SPRING_SECURITY_FORM_USERNAME_KEY);
                String password = requestMap.get(SPRING_SECURITY_FORM_PASSWORD_KEY);

                /**
                 * todo
                 * 自己做登录检查，在checkLogin中可以抛出更详细的异常，然后在此处接收，
                 * 再抛出一个AuthenticationException异常，就可以在AuthenticationEntryPoint中
                 * 统一处理了。
                 * 自定义的登录检查做完了，再让spring security做一遍登录检查。
                 * 故自己做一遍登录检查不是必须的，只是为了获得更详细的异常信息而已。
                 */
                //userService.checkLogin(username,password);

                //封装账号、密码到UsernamePasswordAuthenticationToken中
                usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);

                // Allow subclasses to set the "details" property
//                setDetails(request, usernamePasswordAuthenticationToken);
                /**
                 * 由AuthenticationManager去做认证判断,
                 * 认证后，由认证成功或者失败处理器进行处理.
                 */
                return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);

            }

            return null;
        } else {
            if(logger.isDebugEnabled()){
                logger.debug("非json格式数据，由默认用户名密码过滤器尝试认证.");
            }
            //非json数据，而是form表单，直接使用默认的UsernamePasswordAuthenticationFilter
            return super.attemptAuthentication(request,response);
        }
    }


    /**
     * 重写父类的父类AbstractAuthenticationProcessingFilter中认证成功后的处理逻辑
     * 去掉往session中放用户信息：SecurityContextHolder.getContext().setAuthentication(authResult);
     * 去掉记住我功能
     */
    /**
     *也可以单独写一个successHandler去处理；
     * 因为successfulAuthentication方法最后也是要调用successHandler的。
     * 这里选择单独创建一个successHandler去实现认证成功后的处理。
     */
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
//    }
}
