package security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("12345")).roles("VIP1","VIP2")
                .and().withUser("lisi").password(new BCryptPasswordEncoder().encode("123")).roles("VIP2","VIP3")
                .and().withUser("wangwu").password(new BCryptPasswordEncoder().encode("111")).roles("VIP1","VIP3");
    }

    //定义授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制请求的授权规则
        //访问/,所有人都可以
        http.authorizeRequests().antMatchers("/").permitAll()
                //访问/level1/**，必须有VIP1的角色
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登录功能，会有一个默认的登录页面
//        http.formLogin();
        //如果没有登录，没有权限通过loginPage来到指定页面
        http.formLogin()
                //form表单的用户名:<input name="username"/>，可以修改
                .usernameParameter("username").passwordParameter("password")
                .loginPage("/userlogin");
        /**
         * 1、/login来到登录页
         * 2、重定向到/login?error表示登录失败
         */

        //开启自动配置的注销功能，会去到一个默认的登录页面
//        http.logout();
        //指定注销以后要去的页面
        http.logout().logoutSuccessUrl("/");
        //1、访问/logout表示用户注销，清空session
        //2、注销成功会返回login?logout页面
        //3、
        //4、默认post形式的/login代表处理登录
        //5、一但定制了loginPage，那么loginPage的请求就是登录,也就是说确认用户名密码的连接就是<form th:action="@{/userlogin}" action="" method="post">

        //开启记住我功能，登录成功以后，将cookie发给浏览器，以后访问带上这个cookie，只要通过检查就可以免登陆，点击注销以后会删除
        http.rememberMe().rememberMeParameter("remeber");

    }
}
