package com.green.SecondLife.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{

        security.csrf(csrf -> csrf.disable()) //csrf 공격에 대한 보안 해제
                //여기서부터 인증가 인가에 대한 설정
                .authorizeHttpRequests(authorize ->
                        //모든 요청에 대해서 인증 해제
                        //authorize.anyRequest().permitAll()

                        // /joinForm, /loginForm
                        // /join, /login 요청에 대해서는 인증 해제
                        authorize.anyRequest().permitAll()
                                //나머지 요청에 대해서는 인증 받아야 한다.
                              //  .anyRequest().authenticated()
                )
                //로그인 실행 방법 설정
                .formLogin(login ->
                        login.loginPage("/member/loginForm") //인증을 위한 로그인 페이지 설정
                                .loginProcessingUrl("/member/login") //로그인을 처리하는 url 설정
                                .usernameParameter("memberId") //로그인 시 id로 쓰이는 input태그의 name속성
                                .passwordParameter("memberPW")
                                .defaultSuccessUrl("/member/loginCheck", false) // 로그인 성공 시 이동 url
                                .failureUrl("/member/loginForm") // 로그인 실패 시 이동 url

                ).logout(logout ->
                        // /logout 요청이 발생하면 logout을 시켜줌.
                        logout.logoutUrl("/member/logout")
                                .invalidateHttpSession(true) //로그아웃 시, 세션정보 초기화
                                .logoutSuccessUrl("/")
                );

        return security.build();
    }

    @Bean
    public WebSecurityCustomizer configure(){
        return (web) -> web.ignoring()
                            .requestMatchers("/js/**"
                                            ,"/css/**"
                                            ,"/images/**"
                                            ,"/favicon.ico");
    }

}
