package com.pucp.lab5gtics.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity

public class LogConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/procesarLog")
                .usernameParameter("email")
                .defaultSuccessUrl("/redirecRol",true);
        http.authorizeRequests()
                .antMatchers("/employee","/employee/**").hasAuthority("employee")
                .antMatchers("/manager","/manager/**").hasAuthority("manager");
        http.logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select email, password, permitido from employees where email = ?")
                .authoritiesByUsernameQuery("select email, enabled FROM employees where email = ?  ");
    }

}
