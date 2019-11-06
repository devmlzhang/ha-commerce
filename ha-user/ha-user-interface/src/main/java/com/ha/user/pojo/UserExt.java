package com.ha.user.pojo;


import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class UserExt extends SysUser {

    //权限信息
    private List<SysResource> permissions;

}
