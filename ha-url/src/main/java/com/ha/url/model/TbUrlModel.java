package com.ha.url.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 *
 * </p>
 * @author weirdo
 * @since 2020-01-13
 */
@Data
@ApiModel(value = "TbUrlModel对象", description = "")
public class TbUrlModel implements Serializable {

    private String uuid;

    @ApiModelProperty(value = "缩短后的短址id")
    private String shorlUrlId;

    @ApiModelProperty(value = "原网址")
    private String longUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "上一次访问时间")
    private Date lastView;

    @ApiModelProperty(value = "访问密码")
    private String viewPwd;
}
