package com.cos.photogramstart.config;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 활성화
@Configuration
public class SecurityConfig {

    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean
    BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated() // 해당 주소로 들어가면 인증 필요
                .anyRequest().permitAll() // 그외 요청은 허용
                .and()
                .formLogin()// 접근 권한이 없을 때 로그인 창으로 애동
                .loginPage("/auth/signin") // GET
                .loginProcessingUrl("/auth/signin") // POST
                .defaultSuccessUrl("/") // 정상 로그인시 이동
                .and()
                .oauth2Login() // 일반 로그인도 하고 oauth2 로그인도 할 것이다
                .userInfoEndpoint() //oauth2 로그인하면 최종응답을 회원정보로 바로 받아 --> 코드 X 액세스토큰 X
                .userService(oAuth2DetailsService); // 받을
        return http.build();
    }
}
