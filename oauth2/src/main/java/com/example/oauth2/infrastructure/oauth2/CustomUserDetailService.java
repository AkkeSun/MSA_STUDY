package com.example.oauth2.infrastructure.oauth2;

import com.example.oauth2.adopter.out.AccountEntity;
import com.example.oauth2.adopter.out.AccountRepository;
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

    private final AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AccountEntity account = repository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("invalid userInfo"));
        return new User(account.getUserId(), account.getPassword(), getAuthorities(account));
    }

    public UserDetails loadUserByUsernameAndSnsSync(String userId, String snsSync) {
        AccountEntity account = repository.findByUserIdAndSnsSync(userId, snsSync)
            .orElseThrow(() -> new UsernameNotFoundException("invalid userInfo"));
        // 소셜로그인의 경우 유저 패스워드를 getSnsSecretKey() 로 설정
        return new User(account.getUserId(), account.getSnsSecret(), getAuthorities(account));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(AccountEntity account) {
        return List.of(new SimpleGrantedAuthority(account.getRole().name()));
    }
}
