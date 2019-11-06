package com.ha.user.mapper;

import com.ha.user.pojo.SysResource;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {

    List<SysResource> selectPermissionByUserId(String userId);
}