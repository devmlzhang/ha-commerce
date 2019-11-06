package com.ha.user.api;

import com.ha.common.response.ResponseResult;
import com.ha.user.pojo.SysUser;
import com.ha.user.pojo.UserExt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *    用户服务接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/11
 */
public interface UserApi {

    //根据账号查询用户信息
    @GetMapping("/user/getuserext")
    UserExt getUserext(@RequestParam("username") String username);

    //获取用户名（测试）
    @GetMapping("/user/getUserName")
    String getUserName(@RequestParam("username") String username);

}
