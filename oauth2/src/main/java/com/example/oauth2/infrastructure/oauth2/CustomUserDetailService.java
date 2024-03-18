package com.example.oauth2.infrastructure.oauth2;

import com.example.oauth2.application.port.out.FindAccountPort;
import com.example.oauth2.domain.Account;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final FindAccountPort findAccountPort;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = findAccountPort.findByUserId(userId);
        return new User(account.getUserId(), account.getPassword(), getAuthorities(account));
    }

    public UserDetails loadUserByUsernameAndSnsSync(String userId, String snsSync) {
        Account account = findAccountPort.findByUserIdAndSnsSync(userId, snsSync);
        // 소셜로그인의 경우 유저 패스워드를 getSnsSecretKey() 로 설정
        return new User(account.getUserId(), account.getSnsSecret(), getAuthorities(account));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Account account) {
        return List.of(new SimpleGrantedAuthority(account.getRole().name()));
    }
}
