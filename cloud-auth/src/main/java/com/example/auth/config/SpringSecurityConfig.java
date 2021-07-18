package com.example.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;

@Configurable
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    UserDetailsService customUserDetailsService;
    @Resource
    AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Resource
    AuthenticationFailureHandler customAuthenticationFailureHandler;
    /**
     * 认证管理器：
     * 1、认证信息提供方式（用户名、密码、当前用户的资源权限）
     * 2、可采用内存存储方式，也可能采用数据库方式等
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        数据库存储的密码必须是加密后的，不然会报错：There is no PasswordEncoder mapped for the id "null"
//        String password = passwordEncoder().encode("123456");
//        logger.info("加密之后存储的密码：" + password);
//        auth.inMemoryAuthentication().withUser("zhangsan")
//                .password(password).authorities("ADMIN");
        auth.userDetailsService(customUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 设置默认的加密方式
         return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic() // 采用 httpBasic认证方式
        // 校验手机验证码过滤器
        http
//                .addFilterBefore(mobileValidateFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登录方式
//                .loginPage(securityProperties.getAuthentication().getLoginPage())
//                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl()) // 登录表单提交处理url, 默认是/login
//                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter()) //默认的是 username
//                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())  // 默认的是 password
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .authorizeRequests() // 认证请求
                .antMatchers(
                        "/auth/login"
//                        securityProperties.getAuthentication().getLoginPage(),
//                //      "/code/image","/mobile/page", "/code/mobile"
//                        securityProperties.getAuthentication().getImageCodeUrl(),
//                        securityProperties.getAuthentication().getMobilePage(),
//                        securityProperties.getAuthentication().getMobileCodeUrl()
                ).permitAll() // 放行/login/page不需要认证可访问
                .anyRequest().authenticated() //所有访问该应用的http请求都要通过身份认证才可以访问
                .and()
                .rememberMe() // 记住功能配置
//                .tokenRepository(jdbcTokenRepository()) //保存登录信息
//                .tokenValiditySeconds(securityProperties.getAuthentication().getTokenValiditySeconds()) //记住我有效时长
                .and()
//                .sessionManagement()// session管理
//                .invalidSessionStrategy(invalidSessionStrategy) //当session失效后的处理类
//                .maximumSessions(1) // 每个用户在系统中最多可以有多少个session
//                .expiredSessionStrategy(sessionInformationExpiredStrategy)// 当用户达到最大session数后，则调用此处的实现
//                .maxSessionsPreventsLogin(true) // 当一个用户达到最大session数,则不允许后面再登录
//                .sessionRegistry(sessionRegistry())
//                .and().and()
//                .logout()
//                .addLogoutHandler(customLogoutHandler) // 退出清除缓存
//                .logoutUrl("/user/logout") // 退出请求路径
//                .logoutSuccessUrl("/mobile/page") //退出成功后跳转地址
//                .deleteCookies("JSESSIONID") // 退出后删除什么cookie值
        ;// 注意不要少了分号

        http.csrf().disable(); // 关闭跨站请求伪造
        //将手机认证添加到过滤器链上
//        http.apply(mobileAuthenticationConfig);
    }
}

