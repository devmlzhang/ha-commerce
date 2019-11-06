package com.ha.auth.client;

import com.ha.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *    获取用户信息
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
@FeignClient(value = "ha-user")
public interface UserClient extends UserApi {

}
