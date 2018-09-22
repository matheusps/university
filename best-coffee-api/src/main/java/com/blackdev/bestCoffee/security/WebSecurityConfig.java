package com.blackdev.bestCoffee.security;

import com.blackdev.bestCoffee.credential.CredentialService;
import com.blackdev.bestCoffee.owner.OwnerService;
import com.blackdev.bestCoffee.security.Filters.JWTAuthorizationFilter;
import com.blackdev.bestCoffee.security.Filters.JWTLoginFilter;
import com.blackdev.bestCoffee.user.User;
import com.blackdev.bestCoffee.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = CredentialService.class)
@ComponentScan(basePackageClasses = UserService.class)
@ComponentScan(basePackageClasses = OwnerService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CredentialService service;

    @Autowired
    private UserService userService;

    @Autowired
    private OwnerService ownerService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/auth/login", "/api/user/register", "/api/owner/register").permitAll()
                .anyRequest().authenticated()
                .and()


                 // ** Login Request Filters **
                .addFilterBefore(new JWTLoginFilter("/api/auth/login", authenticationManager(), service, userService, ownerService),
                        UsernamePasswordAuthenticationFilter.class)


                // ** Other Request Filters (Need JWT) **
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), service));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }
}
