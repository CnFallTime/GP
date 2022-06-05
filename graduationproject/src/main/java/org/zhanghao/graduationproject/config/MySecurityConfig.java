package org.zhanghao.graduationproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.zhanghao.graduationproject.bean.MyPasswordEncoder;
import org.zhanghao.graduationproject.service.AuthService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 张昊
 * @date 2019/10/6 - 21:49
 **/
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private Redom redom;
    @Bean
    public UserDetailsService service(){
        return new AuthService();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/vippage/**").hasRole("1");
        //登录功能
        http.formLogin().usernameParameter("name").passwordParameter("password").loginPage("/pwd");

        //权限不足跳转页
        http.exceptionHandling().accessDeniedPage("/getpwd");
        //设置注销成功后来到首页
        //SpringSecurity3.2开始，默认会启动CSRF防护，一旦启动了CSRF防护，“/logout” 需要用post的方式提交，SpringSecurity才能过滤。
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
        //记住我
        http.rememberMe().rememberMeParameter("remembr");
    }


    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(redom.CreateRedom());
//        auth.inMemoryAuthentication().passwordEncoder(
//                new MyPasswordEncoder()).withUser("admin").password(String.valueOf(redom.getPWD())).roles("vip");

        auth.userDetailsService(service()).passwordEncoder(new MyPasswordEncoder());
        //auth.authenticationProvider(username);

    }
}
