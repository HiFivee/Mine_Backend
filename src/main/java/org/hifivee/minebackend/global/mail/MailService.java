package org.hifivee.minebackend.global.mail;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.global.security.AuthCodeService;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final AuthCodeService authCodeService;

    // 인증 코드 메일 발송
    @Async
    public void sendAuthCodeMail(String to) throws Exception {
        MimeMessage message = createAuthCodeMessage(to);

        try {
            javaMailSender.send(message);
            authCodeService.removeExpiredAuthCode();
        } catch (MailException e) {
            throw new IllegalArgumentException("메일 전송 실패");
        }
    }

    // 인증 코드 메일 메시지 작성
    private MimeMessage createAuthCodeMessage(String to) throws Exception {
        String currentAuthCode = authCodeService.generateAuthCode(to);

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        message.setSubject("Mine 서비스 인증 코드: " + currentAuthCode); //제목

        String verificationMessage="";
        verificationMessage += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 10px 0 10px;\"><img src=\"https://avatars.githubusercontent.com/u/103104002?s=400&v=4\" width=\"60px\" height=\"60px\" loading=\"lazy\"></div>";
        verificationMessage += "<div style=\"border-bottom: 0.5px solid gray; padding-right: 30px; padding-left: 30px; margin: 10px 30px 10px 30px;\"></div>";
        verificationMessage += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        verificationMessage += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 인증코드란에 입력하십시오.</p>";
        verificationMessage += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 10px 0 10px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        verificationMessage += currentAuthCode;
        verificationMessage += "</td></tr></tbody></table></div>";
        verificationMessage += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">본 확인 코드는 10분간만 유효합니다.</p>";
        verificationMessage += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">확인 코드는 Mine 서비스의 회원가입, 비밀번호 찾기 등에 사용되므로, 만약, 본인이 요청한 것이 아니라면 본 이메일을 무시하십시오.</p><br/>";
        verificationMessage += "<div style=\"border-bottom: 0.5px solid gray; padding-right: 30px; padding-left: 30px; margin: 10px 30px 10px 30px;\"></div>";
        verificationMessage += "<p style=\"font-size: 13px; color: gray; padding-right: 30px; padding-left: 30px;\">본 이메일은 발신전용입니다.</p>";
        verificationMessage += "<p style=\"font-size: 13px; color: gray; padding-right: 30px; padding-left: 30px;\">©HiFivee. All Rights Reserved.</p><br/>";

        message.setText(verificationMessage, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("ph2ydev@gmail.com","Mine")); //보내는 사람

        return message;
    }

    // 임시 비밀번호 메일 발송
    @Async
    public void sendTempPasswordMail(String to, String tempPassword) throws Exception {
        MimeMessage message = createTempPasswordMessage(to, tempPassword);

        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            throw new IllegalArgumentException("메일 전송 실패");
        }
    }

    // 인증 코드 메일 메시지 작성
    private MimeMessage createTempPasswordMessage(String to, String tempPassword) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to); //보내는 대상
        message.setSubject("Mine 비밀번호 복구: 임시 비밀번호 통지"); //제목

        String verificationMessage="";
        verificationMessage += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 10px 0 10px;\"><img src=\"https://avatars.githubusercontent.com/u/103104002?s=400&v=4\" width=\"60px\" height=\"60px\" loading=\"lazy\"></div>";
        verificationMessage += "<div style=\"border-bottom: 0.5px solid gray; padding-right: 30px; padding-left: 30px; margin: 10px 30px 10px 30px;\"></div>";
        verificationMessage += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">임시 비밀번호 통지</h1>";
        verificationMessage += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래의 임시 비밀번호를 사용하여 로그인 후 반드시 비밀번호를 변경하시기 바랍니다.</p>";
        verificationMessage += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 10px 0 10px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        verificationMessage += tempPassword;
        verificationMessage += "</td></tr></tbody></table></div>";
        verificationMessage += "<div style=\"border-bottom: 0.5px solid gray; padding-right: 30px; padding-left: 30px; margin: 10px 30px 10px 30px;\"></div>";
        verificationMessage += "<p style=\"font-size: 13px; color: gray; padding-right: 30px; padding-left: 30px;\">본 이메일은 발신전용입니다.</p>";
        verificationMessage += "<p style=\"font-size: 13px; color: gray; padding-right: 30px; padding-left: 30px;\">©HiFivee. All Rights Reserved.</p><br/>";

        message.setText(verificationMessage, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("ph2ydev@gmail.com","Mine")); //보내는 사람

        return message;
    }
}
