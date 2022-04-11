package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.Authority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class AccountRequestDto {

    private String email;
    private String password;

    public Account toAccount(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
