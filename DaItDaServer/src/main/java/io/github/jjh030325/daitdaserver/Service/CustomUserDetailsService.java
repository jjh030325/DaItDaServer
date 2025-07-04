package io.github.jjh030325.daitdaserver.Service;

import io.github.jjh030325.daitdaserver.Config.CustomUserDetails;
import io.github.jjh030325.daitdaserver.Domain.UserTable;
import io.github.jjh030325.daitdaserver.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserTable user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));

        return new CustomUserDetails(
                user,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        UserTable user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + id));

        return new CustomUserDetails(
                user,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

}
