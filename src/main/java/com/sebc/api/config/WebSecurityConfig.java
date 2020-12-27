package com.sebc.api.config;

import com.sebc.api.security.JWTAuthorizationFilter;
import com.sebc.api.security.JWTLoginFilter;
import com.sebc.api.service.UserService;
import com.sebc.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }


    /**
     * 密码解析器
     *
     * @return
     */
    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return userService.passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RequestMatcher requestMatcher = new CsrfSecurityRequestMatcher();
        http.csrf().requireCsrfProtectionMatcher(requestMatcher);

        //解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http.authorizeRequests()
                // 可以访问的路径
                .antMatchers("/user/hello", "/user/register", "/user/login").permitAll()
                .anyRequest().authenticated().and()
                // 添加JWT登录拦截器
                .addFilter(new JWTLoginFilter(authenticationManager()))
                // 添加JWT鉴权拦截器
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 异常处理，匿名用户访问无权限资源时的异常
//                .and().exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }


    /**
     * 跨域配置 解决post拦截问题
     *
     * @return 基于URL的跨域配置信息
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 注册跨域配置
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


    class CsrfSecurityRequestMatcher implements RequestMatcher {

        private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

        @Override
        public boolean matches(HttpServletRequest request) {
            List<String> unExecludeUrls = new ArrayList<>();
            //unExecludeUrls.add("/api/test");//（不允许post请求的url路径）此处根据自己的需求做相应的逻辑处理

            if (unExecludeUrls != null && unExecludeUrls.size() > 0) {
                String servletPath = request.getServletPath();
                request.getParameter("");
                for (String url : unExecludeUrls) {
                    if (servletPath.contains(url)) {
                        return true;
                    }
                }
            }
            return allowedMethods.matcher(request.getMethod()).matches();
        }
    }

}

