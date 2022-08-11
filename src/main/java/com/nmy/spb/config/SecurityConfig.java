package com.nmy.spb.config;

import com.nmy.spb.Filter.UserTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * @author nmy
 * @title: SecurityConfig
 * @date 2022-08-06 12:26
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserTokenFilter userTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/accountSecurity/queryVerifyAndUserFull","/accountSecurity/queryVerifyUserPassword","/registered/userRegistered").anonymous()
                .antMatchers(
                        HttpMethod.GET,
                        "/accountSecurity/**",
                        "/",
                        "/appVersion/**",
                        "/attention/**",
                        "/collectbar/**",
                        "/comment/**",
                        "/date/**",
                        "/diary/**",
                        "/follow/**",
                        "/followed/**",
                        "/like/**",
                        "/postbar/**",
                        "/sign/**",
                        "/topic/**",
                        "/user/**",
                        "/upload/**"
                ).permitAll()
                .antMatchers("/accountSecurity/queryVerifyAndUserFull").permitAll()
                .anyRequest().authenticated();
        //  http.exceptionHandling().authenticationEntryPoint(sAuthenticationHanlder);
        http.addFilterBefore(userTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
