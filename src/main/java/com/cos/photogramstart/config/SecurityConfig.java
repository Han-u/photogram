package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "comment/**").authenticated() // 해당 주소로 들어가면 인증 필요
                .anyRequest().permitAll() // 그외 요청은 허용
                .and()
                .formLogin()// 접근 권한이 없을 때 로그인 창으로 애동
                .loginPage("/auth/signin") // GET
                .loginProcessingUrl("/auth/signin") // POST
                .defaultSuccessUrl("/"); // 정상 로그인시 이동
        return http.build();
    }
}
