package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.Authority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class AccountCreateRequestDto {

    private String email;
    private String password;

    public Account toEntity(PasswordEncoder passwordEncoder) {
        return Account.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_UNVERIFIED)
                .nickname(null)
                .phone(null)
                .address(null)
                .position(null)
                .link(null)
                .message(null)
                .techStack(null)
                .experience(null)
                .build();
    }
}
