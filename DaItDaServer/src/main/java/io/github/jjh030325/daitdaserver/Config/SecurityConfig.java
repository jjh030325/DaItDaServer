package io.github.jjh030325.daitdaserver.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 쿠키 기반 인증 비활성화 (필요없음)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**").permitAll() // /user/로 시작하는 요청은 인증없이 허용
                        .anyRequest().authenticated()  // 이외 요쳥은 모두 인증 필요
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 불필요
        return http.build(); // 보안 설정 이후 반환
    }
}
