package com.example.ToikanaService.config;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE)
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public WebSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT t.user_name, t.password, t.is_active FROM users t WHERE t.user_name = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.user_name, ur.name_role" +
                                "FROM user_role ur" +
                                "INNER JOIN users u " +
                                "on ur.user_id = u.id" +
                                "INNER JOIN roles r" +
                                "on ur.role_id = r.id" +
                                "WHERE u.user_name = ? AND u.is_active = 1");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "users/register").permitAll()
                .antMatchers(HttpMethod.POST, "users/auto").permitAll()
                .antMatchers(HttpMethod.GET, "users/{id}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "users/{id}/posts").hasRole("USER")
                .antMatchers(HttpMethod.POST, "users/{id}/comments").hasRole("USER")
                .antMatchers(HttpMethod.POST, "posts/add-post").hasRole("USER")
                .antMatchers(HttpMethod.GET,"post/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"post/").permitAll()
                .antMatchers(HttpMethod.POST,"post/add-comment").hasRole("USER")
                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
