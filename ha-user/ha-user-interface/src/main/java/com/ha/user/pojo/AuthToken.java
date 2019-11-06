package com.ha.user.pojo;

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
    String access_token;
    /**
     * 刷新token
     */
    String refresh_token;
    /**
     * jwt令牌
     */
    String jwt_token;
}
