package com.flata.instagram.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,"/feeds/").permitAll()
            .antMatchers(HttpMethod.GET,"/users/sign-up").permitAll()
            .antMatchers(HttpMethod.GET,"/users/sign-in").permitAll()
            .anyRequest().authenticated()
            .and()
            .headers()
                .disable()
            .csrf()
                .disable()
            .formLogin()
                .disable()
            .httpBasic()
                .disable()
            .rememberMe()
                .disable()
            .logout()
                .disable()
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}