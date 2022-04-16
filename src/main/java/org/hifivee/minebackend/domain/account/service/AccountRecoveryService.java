package org.hifivee.minebackend.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountPasswordRecoverRequestDto;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.global.mail.MailService;
import org.hifivee.minebackend.global.security.AuthCodeService;
import org.hifivee.minebackend.global.security.TempCodeGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountRecoveryService {

    private final AccountRepository accountRepository;
    private final AuthCodeService authCodeService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void recoverPassword(AccountPasswordRecoverRequestDto requestDto) throws Exception {
        // 이메일 기준으로 가입 정보 확인
        Account account = accountRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // 인증 코드 검증
        // 인증 코드가 맞지 않으면 Exception 발생함
        authCodeService.validateAuthCode(account.getEmail(), requestDto.getAuthCode());

        // 임시 비밀번호 발급
        String tempPassword = createTempPassword();

        // 임시 비밀번호 메일로 전송
        mailService.sendTempPasswordMail(account.getEmail(), tempPassword);

        // 임시 비밀번호로 업데이트
        account.updatePassword(passwordEncoder, tempPassword);
    }

    private String createTempPassword() {
        char[] tempPasswordCharSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '!','@','#','$','%','^','&'
        };
        return TempCodeGenerator.generate(tempPasswordCharSet, 8);
    }
}
