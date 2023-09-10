package com.sj.book.web.config.auth;

import com.sj.book.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .csrf().disable()    
                .headers().frameOptions().disable()
                // csrf 와 headers().frameOptions() 는 h2-console 을 사용하기 위해 disable
                .and()
                    .authorizeRequests()
                    // antMatchers : 권한 관리 대상을 지정하는 옵션. URL, HTTTP 메소드 별로 관리 가능
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    // /api/v1/** 주소를 가진 api 는 USER 권한을 가진 사람만 허용
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()  // authenticated : 인증된 사용자 (로그인한 사용자)만 허용
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
    
}
