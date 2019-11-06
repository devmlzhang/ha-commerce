package com.ha.user.controller;

import com.ha.common.enums.ResultEnum;
import com.ha.common.exception.ExceptionCast;
import com.ha.common.response.ResponseResult;
import com.ha.user.dto.LoginRequest;
import com.ha.user.pojo.AuthToken;
import com.ha.user.pojo.UserExt;
import com.ha.user.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *    用户接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
@RestController
@RequestMapping("user")
@Api(value = "用户相关操作")
public class UserController {

    @Autowired
    private UserService  userService;

    @GetMapping("/getuserext")
    public UserExt getUserext(@RequestParam("username") String username) {
        return userService.getUserExt(username);
    }

    @GetMapping("/getUserName")
    public String getUserName(@RequestParam("username") String username) {
        return username;
    }



}
