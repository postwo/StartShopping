package com.example.startshopping.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        // 로그인 처리하기
        http.formLogin(form -> form
                .loginPage("/member/login")
                .defaultSuccessUrl("/") //로그인 성공시
                .failureUrl("/member/login/error")//로그인 실패시
                .usernameParameter("email") //로그인 페이지에서 받아오는 매개변수
                .passwordParameter("password")
                .permitAll());

        //로그아웃
        http.logout(Customizer.withDefaults());

        // 각 페이지에 대한 접근 권한 설정
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/", "/member/**", "/item/**", "/images/**","/register/**").permitAll() //ajax 요청명도 여기다 써줘야한다
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated());


        //403페이지 처리
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        return http.build();

    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





}