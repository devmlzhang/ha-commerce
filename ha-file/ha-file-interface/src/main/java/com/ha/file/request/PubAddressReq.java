package com.ha.file.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *    地址信息请求类
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@Data
@ApiModel(value = "")
public class PubAddressReq implements Serializable {


    @ApiModelProperty(value = "省ID")
    private String provinceId;


    @ApiModelProperty(value = "市ID")
    private String cityId;

    @ApiModelProperty(value = "县ID")
    private String districtId;

    @ApiModelProperty(value = "街道名称")
    private String street;

    @ApiModelProperty(value = "业务id")
    private String bizId;

    @ApiModelProperty(value = "业务类型")
    private Integer bizType;
}
