package org.hifivee.minebackend.global.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

@Data
@AllArgsConstructor
public class AuthCodeToken {
    private String source;
    private String authCode;
    private Calendar expireTime;
}
