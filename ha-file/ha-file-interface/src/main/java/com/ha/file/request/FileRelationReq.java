package com.ha.file.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 *  文件请求实体
 * </p>
 *
 * @author ML.Zhang
 * @since  2019/9/29
 */
@ApiModel
@Data
public class FileRelationReq {


    @ApiModelProperty(value = "业务表关联id")
    private String relId;

    @ApiModelProperty(value = "文件id")
    private String fileId;

    @ApiModelProperty(value = "文件类型")
    private String type;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件扩展名")
    private String fileExt;

    @ApiModelProperty(value = "文件大小")
    private String fileSize;


}