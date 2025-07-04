package io.github.jjh030325.daitdaserver.Config;

import io.github.jjh030325.daitdaserver.Service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Authorization 헤더 추출
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 이후

            if (jwtUtil.validateToken(token)) {
                Long id = jwtUtil.getId(token);
                String role = jwtUtil.getUserRole(token);

                System.out.println("Token valid for user id: " + id + ", role: " + role);

                // 2. UserDetails 객체를 통해 인증 정보 구성
                var userDetails = userDetailsService.loadUserById(id);

                System.out.println("Loaded userDetails: " + userDetails.getUsername());

                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 3. SecurityContext에 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Token invalid");
            }
        }
        filterChain.doFilter(request, response);
    }
}
