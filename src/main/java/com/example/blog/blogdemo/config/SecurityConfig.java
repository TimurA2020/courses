package com.example.blog.blogdemo.config;

import com.example.blog.blogdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(proxyTargetClass = true, prePostEnabled = true, securedEnabled = true)
public class SecurityConfig{

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        http.exceptionHandling().accessDeniedPage("/forbidden");
        http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll();

        http.formLogin()
                .loginProcessingUrl("/login")            //<form action = "/login" method = "post">
                .usernameParameter("user_email")        //<input type = "text" name = "user_email">
                .passwordParameter("user_password")     //<input type = "password" name = "user_password">
                .defaultSuccessUrl("/")          // response.sendRedirect("/")
                .failureUrl("/enter?loginerror")        // response.sendRedirect("/enter?error");
                .loginPage("/enter").permitAll();       // /enter

        http.logout()
                .logoutUrl("/logout")                    //<form action = "/logout" method = "post">
                .logoutSuccessUrl("/enter");            // response.sendRedirect("/enter");

        http.csrf().disable();

        return http.build();
    }

}


//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.exceptionHandling().accessDeniedPage("/forbidden");
//
//        http.authorizeRequests().antMatchers("/css/**", "/js/**").permitAll();
//        http.formLogin()
//                .loginProcessingUrl("/login")      //<form action = "/auth" method = "post>
//                .usernameParameter("user_email")
//                .passwordParameter("user_password")
//                .defaultSuccessUrl("/profile")
//                .failureUrl("/enter?loginerror")
//                .loginPage("/enter").permitAll();
//
//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/enter");
//    }
//
//}
