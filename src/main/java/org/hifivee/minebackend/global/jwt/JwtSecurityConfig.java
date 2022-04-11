package org.hifivee.minebackend.global.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    // TokenProvider 를 주입받은 JwtFilter 를 Security Filter 앞에 추가
    @Override
    public void configure(HttpSecurity http) {
        JwtCustomFilter customJwtFilter = new JwtCustomFilter(jwtTokenProvider);
        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
