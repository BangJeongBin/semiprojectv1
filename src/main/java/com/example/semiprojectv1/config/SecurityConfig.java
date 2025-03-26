package com.example.semiprojectv1.config;

import com.example.semiprojectv1.custom.CustomAuthenticationFailureHandler;
import com.example.semiprojectv1.custom.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;


    // SecurityFilterChain : 스프링 시큐리티에서 적용된 보안규칙들을 필터로 구현해 둔 것
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf().disable() // CSRF 필터 끔(PostMan API 확인을 위해)
                    .userDetailsService(userDetailsService) // userDetailService 설정
                    .authorizeRequests() // URL 기반 인가 설정
                    .antMatchers("/member/logout", "/member/myinfo", "/board/write", "/gallery/write").authenticated() // 인증받은 사용자만 접근 가능
                    .antMatchers("/", "/member/**", "/gallery/**", "/board/**").permitAll() // 인증/인가 여부와 상관 없이 접근 가능
                    .antMatchers("/", "/css/**", "/js/**", "/image/**", "/favicon.ico").permitAll() // 인증/인가 여부와 상관 없이 접근 가능
                .and()
                .formLogin() // form login 인증 사용
                    .loginPage("/member/login") // 커스텀 로그인 페이지 경로
                    .usernameParameter("userid") // 아이디 매개변수 지정
                    .passwordParameter("passwd") // 비밀번호 매개변수 지정
                    .defaultSuccessUrl("/member/myinfo") // 로그인 성공 시 리다이렉트 URL
                    .failureUrl("/member/loginfail") // 로그인 실패 시 리다이렉트 URL
                    .permitAll()
                    .successHandler(successHandler)
                    .failureHandler(failureHandler)
                .and()
                .logout() // 로그아웃 설정
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 URL 지정
                    .logoutSuccessUrl("/") // 로그아웃 성공 후 리다이렉트 될 URL
                    .invalidateHttpSession(true) // 세션 무효화
                    .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                .permitAll();

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() { // 비밀번호 암호화에 사용할 인코더
        return new BCryptPasswordEncoder();
    }
}
