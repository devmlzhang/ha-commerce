package com.ha.user.service.impl;

import com.ha.user.mapper.SysResourceMapper;
import com.ha.user.mapper.SysUserMapper;
import com.ha.user.pojo.SysResource;
import com.ha.user.pojo.SysUser;
import com.ha.user.pojo.UserExt;
import com.ha.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 *  用户实现类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/10/14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;




    @Override
    public UserExt getUserExt(String username) {
        //根据账号查询xcUser信息
        SysUser user;
        SysUser record = new SysUser();
        record.setUsername(username);
        user = this.sysUserMapper.selectOne(record);
        if(user == null){
            return null;
        }
        //用户id
        String userId = user.getId();
        //查询用户所有权限
        List<SysResource> xcMenus = sysResourceMapper.selectPermissionByUserId(userId);


        UserExt xcUserExt = new UserExt();
        BeanUtils.copyProperties(user,xcUserExt);
        //设置权限
        xcUserExt.setPermissions(xcMenus);
        return xcUserExt;

    }


}
