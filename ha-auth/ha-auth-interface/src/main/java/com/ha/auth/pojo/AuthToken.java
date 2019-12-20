package com.ha.auth.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class AuthToken {
    /**
     * 访问token就是短令牌，用户身份令牌
     */
    String accessToken;
    /**
     * 刷新token
     */
    String refreshToken;
    /**
     * jwt令牌
     */
    String jwtToken;
}
