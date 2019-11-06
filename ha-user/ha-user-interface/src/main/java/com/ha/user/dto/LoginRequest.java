package com.ha.user.dto;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class LoginRequest {

    String username;
    String password;
    String verifycode;

}
