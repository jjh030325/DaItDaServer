package io.github.jjh030325.daitdaserver.Config;

import io.github.jjh030325.daitdaserver.Domain.UserTable;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserTable user, Collection<? extends GrantedAuthority> authorities) {
        this.id = user.getId();
        this.username = user.getUserId(); // 로그인용 ID
        this.password = user.getPassword();
        this.authorities = authorities;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
