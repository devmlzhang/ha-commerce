package com.ha.user.service;


import com.ha.user.pojo.UserExt;

/**
 * <p>
 *  用户接口
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
public interface UserService {

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    public UserExt getUserExt(String username);







}
